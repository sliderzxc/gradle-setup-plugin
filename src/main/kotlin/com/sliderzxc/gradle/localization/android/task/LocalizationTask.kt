package com.sliderzxc.gradle.localization.android.task

import com.sliderzxc.gradle.defaults.requireLocalizations
import com.sliderzxc.gradle.localization.core.xml.parser.XmlParser
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
        val localizedValuesFile = File(coreProjectDirectory, "src/main/res/values/some.txt")

        localizedValuesFile.appendText("Hello Localization Plugin")

        englishStrings.forEachIndexed { index, s ->
            localizedValuesFile.appendText("$index | $s\n")
        }
    }
}