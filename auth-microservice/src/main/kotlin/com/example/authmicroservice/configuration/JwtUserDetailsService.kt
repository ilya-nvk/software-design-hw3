package com.example.authmicroservice.configuration

import com.example.authmicroservice.data.repository.UserRepository
import com.example.authmicroservice.domain.exception.NotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username!!)?:throw NotFoundException()
        return User(
            user.email,
            user.password,
            Collections.emptyList()
        )
    }
}