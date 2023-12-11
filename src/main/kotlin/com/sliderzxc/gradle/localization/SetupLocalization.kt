package com.sliderzxc.gradle.localization

import com.sliderzxc.gradle.defaults.extra.setExtraLocalization
import com.sliderzxc.gradle.defaults.requireLocalizations
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import org.gradle.api.Project

fun Project.setupLocalization(
    localizationConfig: LocalizationConfig = requireLocalizations()
) = setExtraLocalization(localizationConfig)