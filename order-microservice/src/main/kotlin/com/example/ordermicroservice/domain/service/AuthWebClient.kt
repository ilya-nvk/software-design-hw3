package com.example.ordermicroservice.domain.service

interface AuthWebClient {
    fun getPersonId(token: String): Int
}