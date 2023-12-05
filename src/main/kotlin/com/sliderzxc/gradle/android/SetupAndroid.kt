package com.sliderzxc.gradle.android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.lint.AndroidLintTask
import com.android.build.gradle.internal.lint.AndroidLintTextOutputTask
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.setupAndroidCommon(config: AndroidConfig) {
    extensions.configure<BaseExtension> {
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
