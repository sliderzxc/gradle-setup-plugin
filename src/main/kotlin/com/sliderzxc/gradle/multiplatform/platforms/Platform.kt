package com.sliderzxc.gradle.multiplatform.platforms

import com.sliderzxc.gradle.multiplatform.MultiplatformConfigurator

/**
 * Enumeration representing different platforms.
 */
enum class Platform {
    Android, Jvm, Js
}

/**
 * Extension function to convert a set of platforms into a MultiplatformConfigurator.
 *
 * @return MultiplatformConfigurator configured based on the specified platforms.
 * @throws IllegalArgumentException if the set of platforms is null or empty.
 */
internal fun Set<Platform>?.toTargets(): MultiplatformConfigurator {
    if (this.isNullOrEmpty()) error("Platforms not found")

    return MultiplatformConfigurator {
        for (platform in this@toTargets) {
            when (platform) {
                Platform.Android -> androidTarget()
                Platform.Jvm -> jvm()
                Platform.Js -> {
                    js(IR) {
                        browser()
                        nodejs()
                    }
                }
            }
        }
    }
}