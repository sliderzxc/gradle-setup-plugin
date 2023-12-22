package com.sliderzxc.gradle.localization.core.result

import com.sliderzxc.gradle.localization.core.config.LocalizationLanguage
import com.sliderzxc.gradle.localization.core.xml.parser.ParserXMLContent

/**
 * Data class representing the result of the localization process.
 *
 * @param languages List of Language objects representing localized content for different languages.
 */
data class LocalizationResult(
    val languages: List<Language>
)

/**
 * Data class representing localized content for a specific language.
 *
 * @param localizationLanguage The language associated with the localized content.
 * @param content List of ParserXMLContent objects representing the parsed XML content for the language.
 */
data class Language(
    val localizationLanguage: LocalizationLanguage,
    val content: List<ParserXMLContent>
)