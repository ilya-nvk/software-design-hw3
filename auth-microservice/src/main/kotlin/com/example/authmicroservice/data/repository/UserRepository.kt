package com.example.authmicroservice.data.repository

import com.example.authmicroservice.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>{
    fun findByEmail(email: String): User?
}