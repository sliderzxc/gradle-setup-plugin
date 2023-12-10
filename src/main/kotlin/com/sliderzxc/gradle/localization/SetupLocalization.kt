package com.sliderzxc.gradle.localization

import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import com.sliderzxc.gradle.localization.config.LocalizationType
import org.gradle.api.Project

fun Project.setupLocalization(
    localizationConfig: LocalizationConfig = requireDefaults()
) {
    when(localizationConfig.type) {
        LocalizationType.XML -> {  }
    }
}