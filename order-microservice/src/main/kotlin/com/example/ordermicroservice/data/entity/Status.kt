package com.example.ordermicroservice.data.entity

enum class Status(private val value: Int) {
    CHECK(0),
    SUCCESS(1),
    REJECTION(2);

    fun getValue(): Int {
        return value
    }
}
