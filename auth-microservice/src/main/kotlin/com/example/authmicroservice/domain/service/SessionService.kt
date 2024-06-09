package com.example.authmicroservice.domain.service

import java.time.LocalDateTime

interface SessionService {
    fun create(username: String, token: String, expired: LocalDateTime)
}