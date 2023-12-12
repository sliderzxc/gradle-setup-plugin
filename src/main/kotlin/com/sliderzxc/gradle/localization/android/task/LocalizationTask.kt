package com.sliderzxc.gradle.localization.android.task

import com.sliderzxc.gradle.defaults.requireLocalizations
import com.sliderzxc.gradle.localization.core.translator.GoogleTranslateRepository
import com.sliderzxc.gradle.localization.core.xml.parser.XmlParser
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        val localizationConfig = project.requireLocalizations()
        val coreProjectDirectory = project.layout.projectDirectory.toString()
        val coreFile = File(coreProjectDirectory, "src/main/res/values/strings.xml").readText()
        val englishStrings = XmlParser.parseXml(coreFile.trimIndent())

        val a = runBlocking {
            val langs = mutableListOf<Lang>()
            localizationConfig.languages.forEach { lang ->
                val strings = mutableListOf<String>()
                englishStrings.forEach { string ->
                    strings.add(GoogleTranslateRepository.getTranslation(lang.code, string).toString())
                }
                langs.add(Lang(strings))
                strings.clear()
            }
            LocalizationResult(langs)
        }

        val localizedValuesFile = File(coreProjectDirectory, "src/main/res/values/some.txt")
        localizedValuesFile.appendText(a.toString())
//
//        englishStrings.forEachIndexed { index, s ->
//            localizedValuesFile.appendText("$index | $s\n")
//        }
    }
}

data class LocalizationResult(
    val lang: List<Lang>
)

data class Lang(
    val content: List<String>
)