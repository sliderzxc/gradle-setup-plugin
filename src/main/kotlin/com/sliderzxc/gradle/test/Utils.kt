package com.sliderzxc.gradle.test

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import kotlin.reflect.KClass

internal val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

internal inline fun Project.hasExtension(clazz: () -> KClass<*>): Boolean =
    try {
        extensions.findByType(clazz()) != null
    } catch (e: NoClassDefFoundError) {
        false
    }

internal fun Project.checkIsRootProject() {
    check(rootProject == this) { "Must be called on a root project" }
}
