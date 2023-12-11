package com.sliderzxc.gradle.localization.android

import com.sliderzxc.gradle.defaults.extra.getLocalizationConfig
import com.sliderzxc.gradle.defaults.extra.setExtraLocalization
import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import org.gradle.api.Project

fun Project.setupAndroidLocalization(
    localizationConfig: LocalizationConfig? = getLocalizationConfig()
) = setExtraLocalization(localizationConfig)