package com.example.ordermicroservice.data.repository

import com.example.ordermicroservice.data.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Int> {
}