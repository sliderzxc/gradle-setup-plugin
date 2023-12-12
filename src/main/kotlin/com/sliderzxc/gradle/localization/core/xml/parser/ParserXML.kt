package com.sliderzxc.gradle.localization.core.xml.parser

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

object XmlParser {
    fun parseXml(xmlString: String): List<ParserXMLContent> {
        val contentList = mutableListOf<ParserXMLContent>()

        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser: XmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlString))

            var eventType = xmlPullParser.eventType
            var currentId: String? = null
            var currentString: String? = null

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (xmlPullParser.name == "string") {
                            currentId = xmlPullParser.getAttributeValue(null, "name")
                            currentString = ""
                        }
                    }
                    XmlPullParser.TEXT -> {
                        currentString?.let { currentString += xmlPullParser.text }
                    }
                    XmlPullParser.END_TAG -> {
                        if (xmlPullParser.name == "string") {
                            currentId?.let { id ->
                                currentString?.let { string ->
                                    contentList.add(ParserXMLContent(id, string))
                                }
                            }
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return contentList
    }
}

data class ParserXMLContent(
    val key: String,
    val value: String
)

fun main() {
    val xmlString = """
         <resources>
             <string name="app_name">FitTrack</string>
             <string name="app_name1">FitTrack1</string>
         </resources>
     """.trimIndent()

    val contentList = XmlParser.parseXml(xmlString)

    for (content in contentList) {
        println("ID: ${content.key}, String: ${content.value}")
    }
}
