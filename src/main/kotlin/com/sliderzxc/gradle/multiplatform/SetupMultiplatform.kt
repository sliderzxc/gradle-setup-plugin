package com.arkivanov.gradle

import com.sliderzxc.gradle.multiplatform.setupSourceSets
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

fun Project.setupMultiplatform(
    targets: MultiplatformConfigurator = requireDefaults(),
) {
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

        //disableCompilationsOfNeeded()
    }

//    if (isMultiplatformTargetEnabled(Target.ANDROID)) {
//        setupAndroidCommon(requireDefaults())
//    }
}

internal val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

fun interface MultiplatformConfigurator {
    operator fun KotlinMultiplatformExtension.invoke()
}

internal inline fun <reified T : Any> Project.requireDefaults(): T =
    requireNotNull(getDefaults()) { "Defaults not found for type ${T::class}" }

internal inline fun <reified T : Any> Project.getDefaults(): T? =
    getDefaults { it as? T }

private fun <T : Any> Project.getDefaults(mapper: (Any) -> T?): T? =
    getDefaultsList()?.asSequence()?.mapNotNull(mapper)?.firstOrNull()
        ?: parent?.getDefaults(mapper)

@Suppress("UNCHECKED_CAST")
private fun Project.getDefaultsList(): MutableList<Any>? =
    extra.takeIf { it.has(DEFAULTS_KEY) }?.get(DEFAULTS_KEY) as ArrayList<Any>?

private const val DEFAULTS_KEY = "com.sliderzxc.gradle.defaults"