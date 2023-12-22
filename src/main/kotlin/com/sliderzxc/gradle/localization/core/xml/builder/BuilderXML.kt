package com.sliderzxc.gradle.localization.core.xml.builder

import com.sliderzxc.gradle.localization.core.result.Language

/**
 * Object for building XML strings from Language objects.
 */
object BuilderXML {
    /**
     * Builds an XML string from a Language object.
     *
     * @param language The Language object containing localized content.
     * @return The XML string representing the localized content.
     */
    fun buildStringFromLanguage(language: Language): String {
        var value = ""
        language.content.forEach {
            value += "<string name=\"${it.key}\">${it.value}</string>\n\t"
        }
        return value
    }
}