package com.sliderzxc.gradle.defaults.extra

import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

@Suppress("UNCHECKED_CAST")
internal fun Project.getExtraList(): MutableList<Any>? {
    return extra.takeIf {
        it.has(ExtraKeyPath.DEFAULTS_KEY.key)
    }?.get(ExtraKeyPath.DEFAULTS_KEY.key) as ArrayList<Any>?
}

internal fun Project.getLocalizationConfig(): LocalizationConfig? {
    return extra.takeIf {
        it.has(ExtraKeyPath.LOCALIZATIONS_KEY.key)
    }?.get(ExtraKeyPath.LOCALIZATIONS_KEY.key) as LocalizationConfig? ?: parent?.getLocalizationConfig()
}

internal fun Project.setExtraDefaults(defaults: List<Any>) {
    extra.set(ExtraKeyPath.DEFAULTS_KEY.key, defaults)
}

internal fun Project.setExtraLocalization(localizationConfig: LocalizationConfig?) {
    localizationConfig?.let { config -> extra.set(ExtraKeyPath.LOCALIZATIONS_KEY.key, config) }
}

internal enum class ExtraKeyPath(val key: String) {
    DEFAULTS_KEY("com.sliderzxc.gradle.keys.defaults"),
    LOCALIZATIONS_KEY("com.sliderzxc.gradle.keys.localization")
}