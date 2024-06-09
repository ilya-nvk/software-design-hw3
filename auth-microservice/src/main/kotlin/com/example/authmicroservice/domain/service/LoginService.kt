package com.example.authmicroservice.domain.service

import com.example.authmicroservice.web.dto.AuthDto

interface LoginService {
    fun login(dto: AuthDto): String
}