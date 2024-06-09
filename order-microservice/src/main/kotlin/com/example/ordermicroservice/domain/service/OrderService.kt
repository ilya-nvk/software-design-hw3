package com.example.ordermicroservice.domain.service

import com.example.ordermicroservice.data.entity.Order
import com.example.ordermicroservice.web.dto.OrderDto

interface OrderService {
    fun create(request: OrderDto, token: String): Order

    fun updateStatus(id: Int)

    fun get(id: Int): Order
}