package com.sliderzxc.gradle.localization.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

internal abstract class LocalizationTask : DefaultTask() {

    @TaskAction
    fun translate() {
        //project.requireDefaults<>()
        val mProjDir = project.layout.projectDirectory.toString()
        val localizedValuesDir = File(mProjDir, "src/main/res/values/main.txt")
        localizedValuesDir.writeText("Hello Localization Plugin")
    }
}