package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.defaults.extra.getLocalizationConfig
import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import org.gradle.api.Project

/**
 * Retrieves the localization configuration for the project.
 *
 * This function retrieves the localization configuration for the Gradle project.
 * If the configuration is not found, it throws an exception with a descriptive message.
 *
 * @return The localization configuration for the project.
 * @throws IllegalStateException if the localization configuration is not found.
 */
internal fun Project.requireLocalizations(): LocalizationConfig {
    return requireNotNull(getLocalizationConfig()) {
        "Localizations not found for type ${LocalizationConfig::class}"
    }
}