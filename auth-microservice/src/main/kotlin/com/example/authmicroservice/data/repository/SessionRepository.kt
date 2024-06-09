package com.example.authmicroservice.data.repository

import com.example.authmicroservice.data.entity.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Int> {
}