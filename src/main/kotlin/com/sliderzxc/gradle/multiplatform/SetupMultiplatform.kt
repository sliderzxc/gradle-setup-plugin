package com.sliderzxc.gradle.multiplatform

import com.sliderzxc.gradle.multiplatform.platforms.Platform
import com.sliderzxc.gradle.multiplatform.platforms.toTargets
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

fun Project.setupMultiplatform(
    vararg platforms: Platform,
) {
    val targets = platforms.toSet().toTargets()

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
}

internal val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

fun interface MultiplatformConfigurator {
    operator fun KotlinMultiplatformExtension.invoke()
}