package com.sliderzxc.gradle.localization.android

import com.sliderzxc.gradle.defaults.extra.getLocalizationConfig
import com.sliderzxc.gradle.defaults.extra.setExtraLocalization
import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import org.gradle.api.Project

/**
 * Sets up localization for an Android project.
 *
 * This function configures localization for the Gradle project, using the provided or retrieved
 * localization configuration. The configuration is stored as an extra property in the project.
 *
 * @param localizationConfig The localization configuration for the project.
 * If not provided, it attempts to retrieve it from extra properties.
 */
fun Project.setupLocalizationAndroid(
    localizationConfig: LocalizationConfig? = getLocalizationConfig()
) = setExtraLocalization(localizationConfig)
