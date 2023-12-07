package com.sliderzxc.gradle.publishing

import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.platforms.multiplatform.setupMultiplatformPublishing
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.setupPublishing(
    publishingConfig: PublishingConfig = requireDefaults()
) {
    when {
        hasExtension{ KotlinMultiplatformExtension::class } -> {
            setupMultiplatformPublishing(publishingConfig)
        }
        else -> error("Unsupported library type")
    }
}