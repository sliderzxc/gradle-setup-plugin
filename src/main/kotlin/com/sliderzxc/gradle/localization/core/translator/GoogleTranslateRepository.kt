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

object GoogleTranslateRepository {
    private val client = OkHttpClient()

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