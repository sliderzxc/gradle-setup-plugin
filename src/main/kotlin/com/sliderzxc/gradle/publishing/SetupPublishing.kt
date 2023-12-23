package com.sliderzxc.gradle.publishing

import com.android.build.gradle.LibraryExtension
import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.platforms.android.setupAndroidLibraryPublishing
import com.sliderzxc.gradle.publishing.platforms.android.utils.hasExtension
import com.sliderzxc.gradle.publishing.platforms.multiplatform.setupMultiplatformPublishing
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configures the publishing settings for a project based on its type (multiplatform or Android).
 *
 * @param publishingConfig The publishing configuration.
 */
fun Project.setupPublishing(
    publishingConfig: PublishingConfig = requireDefaults()
) {
    when {
        hasExtension { KotlinMultiplatformExtension::class } -> {
            setupMultiplatformPublishing(publishingConfig)
        }
        hasExtension { LibraryExtension::class } -> {
            setupAndroidLibraryPublishing(publishingConfig)
        }
        else -> error("Unsupported library type")
    }
}