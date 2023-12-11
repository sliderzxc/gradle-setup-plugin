package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.defaults.extra.getLocalizationConfig
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import org.gradle.api.Project

internal fun Project.requireLocalizations(): LocalizationConfig {
    return requireNotNull(getLocalizationConfig()) {
        "Localizations not found for type ${LocalizationConfig::class}"
    }
}