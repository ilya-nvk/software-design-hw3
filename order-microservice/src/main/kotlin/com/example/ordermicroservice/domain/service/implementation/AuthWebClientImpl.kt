package com.example.ordermicroservice.domain.service.implementation

import com.example.ordermicroservice.configuration.AuthAppProperties
import com.example.ordermicroservice.domain.model.AuthException
import com.example.ordermicroservice.domain.service.AuthWebClient
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service

@Service
class AuthWebClientImpl(
    private val authAppProperties: AuthAppProperties,
) : AuthWebClient {
    override fun getPersonId(token: String): Int {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(authAppProperties.address)
            .header("Authorization", token)
            .build()
        val response = okHttpClient.newCall(request).execute()
        if (!response.isSuccessful) {
            throw AuthException("Unauthorized")
        }
        val responseBody = response.body?.string()
        return JsonParser.parseString(responseBody).asJsonObject.get("id").asInt
    }
}