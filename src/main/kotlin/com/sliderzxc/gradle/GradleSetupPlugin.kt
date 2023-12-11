package com.sliderzxc.gradle

import com.sliderzxc.gradle.localization.core.task.LocalizationTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleSetupPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register(
            "generateLocalizations", LocalizationTask::class.java
        )
    }
}