package com.sliderzxc.gradle.localization.core.task

import com.sliderzxc.gradle.defaults.requireLocalizations
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        val localizationConfig = project.requireLocalizations()
        val mProjDir = project.layout.projectDirectory.toString()
        val localizedValuesDir = File(mProjDir, "src/main/res/values/main.txt")
        localizedValuesDir.writeText("Hello Localization Plugin")
        localizationConfig.languages.forEachIndexed { index, localizationLanguage ->
            localizedValuesDir.writeText("index: $index | text: ${localizationLanguage.name}")
        }
    }
}