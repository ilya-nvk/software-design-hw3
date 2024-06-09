package com.example.ordermicroservice.domain.service

import com.example.ordermicroservice.data.entity.Station

interface StationService {
    fun create (station: String): Station
    fun get(id: Int): Station
}