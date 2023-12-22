package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.android.config.AndroidConfig
import com.sliderzxc.gradle.defaults.extra.setExtraDefaults
import com.sliderzxc.gradle.defaults.extra.setExtraLocalization
import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import com.sliderzxc.gradle.multiplatform.config.MultiplatformConfig
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import org.gradle.api.Project

/**
 * Sets up default configurations for a Gradle project.
 *
 * @param multiplatformConfig Configuration for multiplatform projects.
 * @param androidConfig Configuration for Android projects.
 * @param publishingConfig Configuration for publishing artifacts.
 * @param localizationConfig Configuration for localization.
 */
fun Project.setupDefaults(
    multiplatformConfig: MultiplatformConfig? = null,
    androidConfig: AndroidConfig? = null,
    publishingConfig: PublishingConfig? = null,
    localizationConfig: LocalizationConfig? = null
) {
    setExtraDefaults(listOfNotNull(androidConfig, multiplatformConfig, publishingConfig))
    setExtraLocalization(localizationConfig)
}