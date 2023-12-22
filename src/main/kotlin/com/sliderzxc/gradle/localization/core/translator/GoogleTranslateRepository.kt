package com.sliderzxc.gradle.localization.core.translator

import com.google.gson.JsonParser
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Object for interacting with the Google Translate API.
 */
object GoogleTranslateRepository {
    private val client = OkHttpClient()

    /**
     * Gets a translation for the specified language and text using the Google Translate API.
     *
     * @param lang The target language code.
     * @param text The text to be translated.
     * @return The translated text, or null if translation fails.
     */
    suspend fun getTranslation(lang: String, text: String): String? {
        val urlString = "https://translate.googleapis.com/translate_a/t?client=gtx&dt=t&sl=en&tl=$lang&q=$text"
        val request = Request.Builder().url(urlString).build()

        return suspendCoroutine { continuation ->
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val jsonResponse = response.body?.string()
                    val jsonArray = JsonParser.parseString(jsonResponse).asJsonArray
                    val translation = jsonArray.firstOrNull()?.asString

                    continuation.resume(translation)
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }
}