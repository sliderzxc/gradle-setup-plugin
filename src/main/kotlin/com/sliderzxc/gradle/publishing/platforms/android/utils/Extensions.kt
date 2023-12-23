package com.sliderzxc.gradle.publishing.platforms.android.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import kotlin.reflect.KClass

/**
 * Checks if the project has an extension of the specified type.
 *
 * @param clazz The class reference of the extension type.
 * @return `true` if the project has an extension of the specified type, `false` otherwise.
 */
internal inline fun Project.hasExtension(clazz: () -> KClass<*>): Boolean {
    return try {
        extensions.findByType(clazz()) != null
    } catch (e: NoClassDefFoundError) {
        false
    }
}