package com.sliderzxc.gradle.localization.core.xml.parser

import org.gradle.internal.impldep.org.codehaus.plexus.util.xml.pull.MXParser
import org.gradle.internal.impldep.org.codehaus.plexus.util.xml.pull.XmlPullParser
import java.io.StringReader

object XmlParser {
    fun parseXml(xmlString: String): List<String> {
        val stringList = mutableListOf<String>()

        try {
            val xmlPullParser: XmlPullParser = MXParser()
            xmlPullParser.setInput(StringReader(xmlString))

            var eventType = xmlPullParser.eventType
            var currentString: String? = null

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (xmlPullParser.name == "string") {
                            currentString = ""
                        }
                    }
                    XmlPullParser.TEXT -> {
                        currentString?.let {
                            currentString += xmlPullParser.text
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (xmlPullParser.name == "string") {
                            currentString?.let {
                                stringList.add(it)
                            }
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringList
    }
}

//fun main() {
//    val xmlString = """
//        <resources>
//            <string name="app_name">FitTrack</string>
//            <string name="app_name1">FitTrack1</string>
//        </resources>
//    """.trimIndent()
//
//    val stringList = parseXml(xmlString)
//
//    for (value in stringList) {
//        println(value)
//    }
//}
