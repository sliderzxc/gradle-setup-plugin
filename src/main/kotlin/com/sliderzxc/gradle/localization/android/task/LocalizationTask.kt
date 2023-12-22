package com.sliderzxc.gradle.localization.android.task

import com.sliderzxc.gradle.defaults.requireLocalizations
import com.sliderzxc.gradle.localization.core.config.LocalizationLanguage
import com.sliderzxc.gradle.localization.core.result.Language
import com.sliderzxc.gradle.localization.core.result.LocalizationResult
import com.sliderzxc.gradle.localization.core.translator.GoogleTranslateRepository
import com.sliderzxc.gradle.localization.core.xml.builder.BuilderXML
import com.sliderzxc.gradle.localization.core.xml.parser.ParserXMLContent
import com.sliderzxc.gradle.localization.core.xml.parser.XmlParser
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
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
            val localizedValuesDirectory = File(
                coreProjectDirectory, "$childPath-${language.localizationLanguage.code}"
            )
            localizedValuesDirectory.mkdirs()

            val localizationFile = File(localizedValuesDirectory, "strings.xml")
            val xmlContent = """<?xml version="1.0" encoding="utf-8"?>
<resources>
    ${BuilderXML.buildStringFromLanguage(language).trimEnd()}
</resources>""".trimIndent()
            localizationFile.writeText(xmlContent)
        }
    }
}

internal fun main() {
    val localizationResult = LocalizationResult(
        listOf(Language(LocalizationLanguage.Ukrainian, listOf(ParserXMLContent("some_name", "some_value"), ParserXMLContent("some_n", "some_v"))))
    )
    for (language in localizationResult.languages) {
        val xmlContent = """<?xml version="1.0" encoding="utf-8"?>
<resources>
    ${BuilderXML.buildStringFromLanguage(language).trimEnd()}
</resources>""".trimIndent()
        println(xmlContent)
    }
}