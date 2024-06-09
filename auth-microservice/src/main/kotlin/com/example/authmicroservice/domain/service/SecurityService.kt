package com.example.authmicroservice.domain.service

import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

interface SecurityService {
    fun generateToken(userDetails: UserDetails, expiration: LocalDateTime): String
    fun extractUsername(token: String): String
}