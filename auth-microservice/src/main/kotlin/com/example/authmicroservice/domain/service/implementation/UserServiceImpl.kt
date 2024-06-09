package com.example.authmicroservice.domain.service.implementation

import com.example.authmicroservice.data.entity.User
import com.example.authmicroservice.data.repository.UserRepository
import com.example.authmicroservice.domain.exception.NotFoundException
import com.example.authmicroservice.domain.service.UserService
import com.example.authmicroservice.web.dto.UserDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) : UserService {
    @Transactional
    override fun create(dto: UserDto): User {
        if (userRepository.findByEmail(dto.email) != null) {
            throw IllegalStateException("person already exists")
        }
        val encodedPassword = passwordEncoder.encode(dto.password)
        return userRepository.save(
            User(
                0,
                dto.nickname,
                dto.email,
                encodedPassword,
                LocalDateTime.now()
            )
        )
    }

    override fun getByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw NotFoundException()
    }
}