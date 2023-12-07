package com.sliderzxc.gradle.multiplatform.platforms

import com.sliderzxc.gradle.multiplatform.MultiplatformConfigurator

enum class Platform {
    Android, Jvm, Js
}

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