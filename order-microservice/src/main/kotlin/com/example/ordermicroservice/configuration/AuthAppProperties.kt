package com.example.ordermicroservice.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "auth.details")
data class AuthAppProperties(
    val address: String
)
