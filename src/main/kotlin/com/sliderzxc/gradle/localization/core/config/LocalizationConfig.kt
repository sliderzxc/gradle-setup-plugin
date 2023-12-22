package com.sliderzxc.gradle.localization.core.config

/**
 * Configuration data class for localization.
 *
 * @param languages Set of supported localization languages.
 */
data class LocalizationConfig(
    val languages: Set<LocalizationLanguage>
)
