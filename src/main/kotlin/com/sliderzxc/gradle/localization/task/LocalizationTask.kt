package com.sliderzxc.gradle.localization.task

import com.sliderzxc.gradle.localization.config.LocalizationLanguage
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @get:Input
    var localizationConfig = listOf<LocalizationLanguage>()

    @TaskAction
    fun translate() {
        val mainFile = File(project.path+"/buildinggg")
        mainFile.writeText("Hello Localization Plugin")
    }
}