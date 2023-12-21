package com.sliderzxc.gradle

import com.sliderzxc.gradle.localization.android.task.LocalizationTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleSetupPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register(
            "generateLocalizations", LocalizationTask::class.java
        ) {
            group = "gradle setup"
        }
    }
}