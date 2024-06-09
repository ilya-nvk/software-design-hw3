package com.example.ordermicroservice.web.dto

import jakarta.validation.constraints.NotNull

data class OrderDto(
    @field:NotNull(message = "ID не должен быть пустым")
    val fromStationId: Int,
    @field:NotNull(message = "ID не должно быть пустым")
    val toStationId: Int
)

