package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.android.config.AndroidConfig
import com.sliderzxc.gradle.defaults.extra.setExtraDefaults
import com.sliderzxc.gradle.defaults.extra.setExtraLocalization
import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import com.sliderzxc.gradle.multiplatform.config.MultiplatformConfig
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import org.gradle.api.Project

fun Project.setupDefaults(
    multiplatformConfig: MultiplatformConfig? = null,
    androidConfig: AndroidConfig? = null,
    publishingConfig: PublishingConfig? = null,
    localizationConfig: LocalizationConfig? = null
) {
    setExtraDefaults(listOfNotNull(androidConfig, multiplatformConfig, publishingConfig))
    setExtraLocalization(localizationConfig)
}