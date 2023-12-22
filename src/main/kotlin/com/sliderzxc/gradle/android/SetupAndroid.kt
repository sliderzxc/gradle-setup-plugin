package com.sliderzxc.gradle.android

import com.android.build.gradle.BaseExtension
import com.sliderzxc.gradle.android.config.AndroidConfig
import com.sliderzxc.gradle.defaults.requireDefaults
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Sets up the config for an Android library project or KMP using platform android.
 *
 * @param config The Android configuration for the project.
 */
fun Project.setupAndroidLibrary(
    config: AndroidConfig = requireDefaults()
) = setupAndroidCommon(config)

internal fun Project.setupAndroidCommon(config: AndroidConfig) {
    extensions.configure<BaseExtension> {
        namespace = config.namespace
        compileSdkVersion(config.compileSdkVersion)

        defaultConfig {
            minSdk = config.minSdkVersion
            targetSdk = config.targetSdkVersion
        }

        compileOptions {
            sourceCompatibility(JavaVersion.VERSION_11)
            targetCompatibility(JavaVersion.VERSION_11)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
