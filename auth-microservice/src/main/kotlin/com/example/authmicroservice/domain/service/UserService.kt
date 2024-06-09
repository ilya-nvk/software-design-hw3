package com.example.authmicroservice.domain.service

import com.example.authmicroservice.data.entity.User
import com.example.authmicroservice.web.dto.UserDto

interface UserService {
    fun create(dto: UserDto): User
    fun getByEmail(email: String): User
}