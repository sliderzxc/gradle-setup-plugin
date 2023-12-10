package com.sliderzxc.gradle.localization.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        //project.requireDefaults<>()
        val mainFile = File(project.path+"/buildinggg")
        mainFile.writeText("Hello Localization Plugin")
    }
}