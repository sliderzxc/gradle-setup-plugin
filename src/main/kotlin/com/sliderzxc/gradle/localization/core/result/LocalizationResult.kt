package com.sliderzxc.gradle.localization.core.result

import com.sliderzxc.gradle.localization.core.config.LocalizationLanguage
import com.sliderzxc.gradle.localization.core.xml.parser.ParserXMLContent

data class LocalizationResult(
    val languages: List<Language>
)

data class Language(
    val localizationLanguage: LocalizationLanguage,
    val content: List<ParserXMLContent>
)