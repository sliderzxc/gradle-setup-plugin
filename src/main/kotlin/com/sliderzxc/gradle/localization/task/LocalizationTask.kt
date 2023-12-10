package com.sliderzxc.gradle.localization.task

import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        val languages = project.requireDefaults<LocalizationConfig>()
        val mProjDir = project.layout.projectDirectory.toString()
        val localizedValuesDir = File(mProjDir, "src/main/res/values/main.txt")
        localizedValuesDir.writeText("Hello Localization Plugin")
        languages.languages.forEachIndexed { index, localizationLanguage ->
            localizedValuesDir.writeText("index: $index | text: ${localizationLanguage.name}")
        }
    }
}