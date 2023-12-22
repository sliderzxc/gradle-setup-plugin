package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.defaults.extra.getExtraList
import org.gradle.api.Project

/**
 * Retrieves the default configuration of a specific type for the project.
 *
 * This function attempts to retrieve the default configuration of the specified type for the Gradle project.
 * If the configuration is not found, it throws an exception with a descriptive message.
 *
 * @return The default configuration of the specified type for the project.
 * @throws IllegalStateException if the default configuration is not found.
 * @param T The type of the default configuration to retrieve.
 */
internal inline fun <reified T : Any> Project.requireDefaults(): T {
    return requireNotNull(getDefaults()) { "Defaults not found for type ${T::class}" }
}

/**
 * Retrieves the default configuration of a specific type for the project.
 *
 * @return The default configuration of the specified type for the project, or null if not found.
 * @param T The type of the default configuration to retrieve.
 */
private inline fun <reified T : Any> Project.getDefaults(): T? = getDefaults { it as? T }

/**
 * Retrieves the default configuration of a specific type for the project using a mapper function.
 *
 * This function is used internally to map and retrieve the default configuration based on a provided mapper.
 *
 * @return The default configuration of the specified type for the project, or null if not found.
 * @param mapper A function that maps an object to the desired type, or null if the mapping is not possible.
 */
private fun <T : Any> Project.getDefaults(mapper: (Any) -> T?): T? {
    return getExtraList()?.asSequence()?.mapNotNull(mapper)?.firstOrNull() ?: parent?.getDefaults(mapper)
}
