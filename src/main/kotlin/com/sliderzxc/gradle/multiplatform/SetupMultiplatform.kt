package com.sliderzxc.gradle.multiplatform

import com.sliderzxc.gradle.android.setupAndroidLibrary
import com.sliderzxc.gradle.defaults.requireDefaults
import com.sliderzxc.gradle.multiplatform.config.MultiplatformConfig
import com.sliderzxc.gradle.multiplatform.platforms.Platform
import com.sliderzxc.gradle.multiplatform.platforms.toTargets
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

/**
 * Configures a Kotlin multiplatform project.
 *
 * @param multiplatformConfig The configuration for the multiplatform project.
 */
fun Project.setupMultiplatform(
    multiplatformConfig: MultiplatformConfig? = requireDefaults()
) {
    val targets = multiplatformConfig?.platforms.toTargets()

    multiplatformExtension.apply {
        with(targets) { invoke() }

        setupSourceSets {
            common.main.dependencies {
                implementation(kotlin("stdlib"))
            }

            common.test.dependencies {
                implementation(kotlin("test"))
            }
        }

        this.targets.configureEach {
            compilations.configureEach {
                compilerOptions.configure {
                    freeCompilerArgs.add("-Xexpect-actual-classes")
                }
            }
        }
    }

    if (multiplatformConfig?.platforms?.contains(Platform.Android) == true) {
        setupAndroidLibrary()
    }
}

/**
 * Gets the KotlinMultiplatformExtension from the project.
 */
internal val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

/**
 * Functional interface for configuring a KotlinMultiplatformExtension.
 */
fun interface MultiplatformConfigurator {
    operator fun KotlinMultiplatformExtension.invoke()
}
