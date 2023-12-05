package com.sliderzxc.gradle.platforms.android

import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

data class AndroidSourceSetBundle(
    val main: KotlinSourceSet,
    val test: KotlinSourceTestSet,
)

data class KotlinSourceTestSet(
    val unit: KotlinSourceSet,
    val ui: KotlinSourceSet
)