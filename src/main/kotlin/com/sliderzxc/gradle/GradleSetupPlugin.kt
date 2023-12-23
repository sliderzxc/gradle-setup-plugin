package com.sliderzxc.gradle

import com.sliderzxc.gradle.localization.android.task.LocalizationTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin for setting up project configurations.
 */
class GradleSetupPlugin : Plugin<Project> {
    /**
     * Applies the plugin to the given project.
     *
     * @param target The project to apply the plugin to.
     */
    override fun apply(target: Project) {
        target.tasks.register(
            "generateLocalizations", LocalizationTask::class.java
        ) {
            group = "gradle setup"
            description = "Task generates strings.xml for all your support languages which you add in build.gradle"
        }
    }
}
