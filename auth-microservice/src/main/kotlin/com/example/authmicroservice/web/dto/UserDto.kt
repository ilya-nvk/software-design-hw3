package com.example.authmicroservice.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class UserDto(
    @field:NotNull(message = "Имя пользователя не должно быть пустым")
    val nickname: String,

    @field:NotNull(message = "Email пользователя не должен быть пустым")
    @field:Email(message = "Email пользователя должен быть валидным")
    val email: String,

    @field:NotNull(message = "Пароль не должен быть пустым")
    @field:Size(min = 8, message = "Размер пароля должен быть не меньше 8 символов")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$",
        message = "Пароль должен содержать только цифры, буквы и спец символы"
    )
    val password: String
)