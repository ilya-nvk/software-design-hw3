package com.example.ordermicroservice.data.repository

import com.example.ordermicroservice.data.entity.Station
import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository : JpaRepository<Station, Int> {
}