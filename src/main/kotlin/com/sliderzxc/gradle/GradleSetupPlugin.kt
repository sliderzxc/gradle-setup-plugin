package com.sliderzxc.gradle

import com.sliderzxc.gradle.localization.android.task.LocalizationTask
import com.sliderzxc.gradle.publishing.task.PublishingTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleSetupPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register(
            "generateLocalizations", LocalizationTask::class.java
        ) {
            group = "gradle setup"
            description = "task generates strings.xml for all your support languages which you add in build.gradle"
        }
    }
}