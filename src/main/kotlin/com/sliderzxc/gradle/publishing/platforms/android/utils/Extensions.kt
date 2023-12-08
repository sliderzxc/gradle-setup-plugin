package com.sliderzxc.gradle.publishing.platforms.android.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import kotlin.reflect.KClass

internal inline fun Project.hasExtension(clazz: () -> KClass<*>): Boolean {
    return try {
        extensions.findByType(clazz()) != null
    } catch (e: NoClassDefFoundError) {
        false
    }
}