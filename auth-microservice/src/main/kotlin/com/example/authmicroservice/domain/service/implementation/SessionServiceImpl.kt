package com.example.authmicroservice.domain.service.implementation

import com.example.authmicroservice.data.entity.Session
import com.example.authmicroservice.data.repository.SessionRepository
import com.example.authmicroservice.domain.service.SessionService
import com.example.authmicroservice.domain.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SessionServiceImpl(
    private val userService: UserService,
    private val sessionRepository: SessionRepository
) : SessionService {
    @Transactional
    override fun create(username: String, token: String, expired: LocalDateTime) {
        val user = userService.getByEmail(username)
        val session = Session(
            0,
            user,
            token,
            expired
        )
        sessionRepository.save(session)
    }
}