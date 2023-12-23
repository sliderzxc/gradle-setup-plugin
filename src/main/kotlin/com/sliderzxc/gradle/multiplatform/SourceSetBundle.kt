package com.sliderzxc.gradle.multiplatform

import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/**
 * Data class representing a bundle of Kotlin source sets for a multiplatform project.
 *
 * @param main The main Kotlin source set.
 * @param test The test Kotlin source set.
 */
data class SourceSetBundle(
    val main: KotlinSourceSet,
    val test: KotlinSourceSet
)