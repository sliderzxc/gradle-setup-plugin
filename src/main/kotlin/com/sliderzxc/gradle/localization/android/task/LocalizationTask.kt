package com.sliderzxc.gradle.localization.android.task

import com.sliderzxc.gradle.defaults.requireLocalizations
import com.sliderzxc.gradle.localization.core.result.Language
import com.sliderzxc.gradle.localization.core.result.LocalizationResult
import com.sliderzxc.gradle.localization.core.translator.GoogleTranslateRepository
import com.sliderzxc.gradle.localization.core.xml.parser.ParserXMLContent
import com.sliderzxc.gradle.localization.core.xml.parser.XmlParser
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.redundent.kotlin.xml.XmlVersion
import org.redundent.kotlin.xml.xml
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        val localizationConfig = project.requireLocalizations()
        val coreProjectDirectory = project.layout.projectDirectory.asFile
        val childPath = "src/main/res/values"
        val coreFile = File(coreProjectDirectory, "$childPath/strings.xml").readText()
        val englishStrings = XmlParser.parseXml(coreFile.trimIndent())

        val localizationResult = runBlocking {
            async {
                val languages = mutableListOf<Language>()
                localizationConfig.languages.forEach { lang ->
                    val xmlContents = mutableListOf<ParserXMLContent>()
                    englishStrings.forEach { parserXMLContent ->
                        val request = GoogleTranslateRepository.getTranslation(
                            lang.code, parserXMLContent.value
                        )
                        xmlContents.add(
                            ParserXMLContent(
                                parserXMLContent.key, request ?: parserXMLContent.value
                            )
                        )
                    }
                    languages.add(Language(lang, xmlContents))
                }
                LocalizationResult(languages)
            }.await()
        }

        localizationResult.languages.forEach { language ->
            val localizationFile = File(
                coreProjectDirectory, "$childPath-${language.localizationLanguage.code}/strings.xml"
            )
            val xmlContent = xml("resources", encoding = "utf-8", version = XmlVersion.V10) {
                language.content.forEach { parserXMLContent ->
                    "string" {
                        attribute(
                            name = parserXMLContent.key,
                            value = parserXMLContent.value
                        )
                    }
                }
            }
            localizationFile.appendText(xmlContent.toString())
        }

        val localizedValuesFile = File(coreProjectDirectory, "src/main/res/values/some.txt")
        localizedValuesFile.writeText(localizationResult.toString())
    }
}