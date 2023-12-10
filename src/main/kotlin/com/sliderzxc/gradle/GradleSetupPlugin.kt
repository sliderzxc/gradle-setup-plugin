package com.sliderzxc.gradle

import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import com.sliderzxc.gradle.localization.task.LocalizationTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleSetupPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val languageList = target.requireDefaults<LocalizationConfig>()
        target.tasks.register(
            "generateLocalizations", LocalizationTask::class.java
        )
    }
}