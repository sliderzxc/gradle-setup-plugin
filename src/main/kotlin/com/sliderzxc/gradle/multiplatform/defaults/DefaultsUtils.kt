package com.sliderzxc.gradle.multiplatform.defaults

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

internal inline fun <reified T : Any> Project.requireDefaults(): T =
    requireNotNull(getDefaults()) { "Defaults not found for type ${T::class}" }

internal inline fun <reified T : Any> Project.getDefaults(): T? {
    return getDefaults { it as? T }
}

private fun <T : Any> Project.getDefaults(mapper: (Any) -> T?): T? {
    return getDefaultsList()?.asSequence()?.mapNotNull(mapper)?.firstOrNull()
        ?: parent?.getDefaults(mapper)
}

@Suppress("UNCHECKED_CAST")
private fun Project.getDefaultsList(): MutableList<Any>? {
    return extra.takeIf { it.has(DEFAULTS_KEY) }?.get(DEFAULTS_KEY) as ArrayList<Any>?
}

private const val DEFAULTS_KEY = "com.sliderzxc.gradle.defaults"