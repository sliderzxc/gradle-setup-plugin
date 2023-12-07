package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.android.config.AndroidConfig
import com.sliderzxc.gradle.multiplatform.config.MultiplatformConfig
import com.sliderzxc.gradle.multiplatform.platforms.Platform
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.setupDefaults(
    multiplatformConfig: MultiplatformConfig? = null,
    androidConfig: AndroidConfig? = null,
) {
    extra.set(
        DEFAULTS_KEY,
        listOfNotNull(
            androidConfig,
            multiplatformConfig
        )
    )
}

fun setupDef(
    multiplatformConfig: MultiplatformConfig? = null,
    androidConfig: AndroidConfig? = null,
) {
    println(multiplatformConfig?.platforms?.joinToString(" | "))
}

fun main() {
    setupDef(
        multiplatformConfig = MultiplatformConfig(
            setOf(Platform.Android, Platform.Js, Platform.Android)
        )
    )
}