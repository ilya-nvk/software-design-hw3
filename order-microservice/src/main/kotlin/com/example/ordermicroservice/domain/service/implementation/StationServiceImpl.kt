package com.example.ordermicroservice.domain.service.implementation

import com.example.ordermicroservice.data.entity.Station
import com.example.ordermicroservice.data.repository.StationRepository
import com.example.ordermicroservice.domain.model.NotFoundException
import com.example.ordermicroservice.domain.service.StationService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StationServiceImpl(
    private val stationRepository: StationRepository
) : StationService {
    override fun create(station: String) : Station {
        return stationRepository.save(Station(0, station))
    }

    override fun get(id: Int): Station {
        return stationRepository.findByIdOrNull(id)?: throw NotFoundException()
    }
}