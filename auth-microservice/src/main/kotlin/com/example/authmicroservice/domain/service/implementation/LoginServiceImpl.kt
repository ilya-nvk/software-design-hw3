package com.example.authmicroservice.domain.service.implementation

import com.example.authmicroservice.configuration.JwtProperties
import com.example.authmicroservice.domain.service.LoginService
import com.example.authmicroservice.domain.service.SecurityService
import com.example.authmicroservice.domain.service.SessionService
import com.example.authmicroservice.web.dto.AuthDto
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class LoginServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val securityService: SecurityService,
    private val sessionService: SessionService,
    private val userDetailsService: UserDetailsService,
    private val jwtProperties: JwtProperties
) : LoginService {
    @Transactional
    override fun login(dto: AuthDto): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                dto.email,
                dto.password
            )
        )
        val userDetails = userDetailsService.loadUserByUsername(dto.email)
        val expiredDate = LocalDateTime.now().plus(jwtProperties.lifetime)
        val token = securityService.generateToken(userDetails, expiredDate)
        sessionService.create(dto.email, token, expiredDate)
        return token
    }
}