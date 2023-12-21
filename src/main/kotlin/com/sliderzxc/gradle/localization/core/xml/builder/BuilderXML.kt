package com.sliderzxc.gradle.localization.core.xml.builder

import com.sliderzxc.gradle.localization.core.result.Language

object BuilderXML {
    fun buildStringFromLanguage(language: Language): String {
        var value = ""
        language.content.forEach {
            value += "<string name=\"${it.key}\">${it.value}</string>\n\t"
        }
        return value
    }
}