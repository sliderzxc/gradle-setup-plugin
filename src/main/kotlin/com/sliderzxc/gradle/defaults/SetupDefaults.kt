package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.android.config.AndroidConfig
import com.sliderzxc.gradle.localization.config.LocalizationConfig
import com.sliderzxc.gradle.multiplatform.config.MultiplatformConfig
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.setupDefaults(
    multiplatformConfig: MultiplatformConfig? = null,
    androidConfig: AndroidConfig? = null,
    publishingConfig: PublishingConfig? = null,
    localizationConfig: LocalizationConfig? = null
) {
    extra.set(
        DEFAULTS_KEY,
        listOfNotNull(
            androidConfig,
            multiplatformConfig,
            publishingConfig,
            localizationConfig
        )
    )
}