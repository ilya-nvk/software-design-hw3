package com.example.authmicroservice.web.dto


import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AuthDto(
    @field:NotNull(message = "Электронная почта пользователя не должна быть пустой")
    @field:Email(message = "Электронная почта пользователя должна быть валидной")
    val email: String,

    @field:NotNull(message = "Пароль не должен быть пустым")
    @field:Size(min = 8, message = "Длина пароля должна быть не меньше 8")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$",
        message = "Пароль должен содержать только цифры, буквы и спец символы"
    )
    val password: String
)