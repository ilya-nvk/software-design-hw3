package com.example.ordermicroservice.domain.service

import com.example.ordermicroservice.data.entity.Order

interface UpdateService {
    fun addToQueue(order: Order)
}