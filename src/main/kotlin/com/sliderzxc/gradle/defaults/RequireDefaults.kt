package com.sliderzxc.gradle.defaults

import com.sliderzxc.gradle.defaults.extra.getExtraList
import org.gradle.api.Project

internal inline fun <reified T : Any> Project.requireDefaults(): T {
    return requireNotNull(getDefaults()) { "Defaults not found for type ${T::class}" }
}
private inline fun <reified T : Any> Project.getDefaults(): T? = getDefaults { it as? T }

private fun <T : Any> Project.getDefaults(mapper: (Any) -> T?): T? {
    return getExtraList()?.asSequence()?.mapNotNull(mapper)?.firstOrNull() ?: parent?.getDefaults(mapper)
}