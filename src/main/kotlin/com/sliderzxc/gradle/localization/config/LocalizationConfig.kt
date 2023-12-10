package com.sliderzxc.gradle.localization.config

data class LocalizationConfig(
    val type: LocalizationType,
    val languages: Set<LocalizationLanguage>
)