package com.sliderzxc.gradle.defaults.extra

import com.sliderzxc.gradle.localization.core.config.LocalizationConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

/**
 * Gets a list of extra configurations stored in the project's extra properties for the given key.
 *
 * @return A mutable list of extra configurations or null if the key is not present.
 */
@Suppress("UNCHECKED_CAST")
internal fun Project.getExtraList(): MutableList<Any>? {
    return extra.takeIf {
        it.has(ExtraKeyPath.DEFAULTS_KEY.key)
    }?.get(ExtraKeyPath.DEFAULTS_KEY.key) as ArrayList<Any>?
}

/**
 * Gets the localization configuration stored in the project's extra properties for the localization key.
 *
 * @return The localization configuration or null if the key is not present.
 */
internal fun Project.getLocalizationConfig(): LocalizationConfig? {
    return extra.takeIf {
        it.has(ExtraKeyPath.LOCALIZATIONS_KEY.key)
    }?.get(ExtraKeyPath.LOCALIZATIONS_KEY.key) as LocalizationConfig? ?: parent?.getLocalizationConfig()
}

/**
 * Sets the extra configurations in the project's extra properties for the defaults key.
 *
 * @param defaults The list of extra configurations to set.
 */
internal fun Project.setExtraDefaults(defaults: List<Any>) {
    extra.set(ExtraKeyPath.DEFAULTS_KEY.key, defaults)
}

/**
 * Sets the localization configuration in the project's extra properties for the localization key.
 *
 * @param localizationConfig The localization configuration to set.
 */
internal fun Project.setExtraLocalization(localizationConfig: LocalizationConfig?) {
    localizationConfig?.let { config -> extra.set(ExtraKeyPath.LOCALIZATIONS_KEY.key, config) }
}

/**
 * Enum representing the keys for accessing extra properties in the project's extra properties container.
 */
internal enum class ExtraKeyPath(val key: String) {
    DEFAULTS_KEY("com.sliderzxc.gradle.keys.defaults"),
    LOCALIZATIONS_KEY("com.sliderzxc.gradle.keys.localization")
}
