package com.example.ordermicroservice.domain.service.implementation

import com.example.ordermicroservice.data.entity.Order
import com.example.ordermicroservice.data.entity.Status
import com.example.ordermicroservice.data.repository.OrderRepository
import com.example.ordermicroservice.domain.model.NotFoundException
import com.example.ordermicroservice.domain.service.OrderService
import com.example.ordermicroservice.domain.service.AuthWebClient
import com.example.ordermicroservice.domain.service.StationService
import com.example.ordermicroservice.web.dto.OrderDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderServiceImpl(
    private val authWebClient: AuthWebClient,
    private val stationService: StationService,
    private val orderRepository: OrderRepository
) : OrderService {
    @Transactional
    override fun create(request: OrderDto, token: String): Order {
        val personId = authWebClient.getPersonId(token)
        val from = stationService.get(request.fromStationId)
        val to = stationService.get(request.toStationId)
        val order = Order(
            0,
            personId,
            from,
            to,
            Status.CHECK,
            LocalDateTime.now()
        )
        return orderRepository.save(order)
    }

    @Transactional
    override fun updateStatus(id: Int) {
        val oldOrder = get(id)
        val newStatus = if ((System.currentTimeMillis() % 1000) > 500) Status.SUCCESS else Status.REJECTION
        val newOrder = Order(
            oldOrder.id,
            oldOrder.userId,
            oldOrder.from,
            oldOrder.to,
            newStatus,
            oldOrder.created
        )
        orderRepository.saveAndFlush(newOrder)
    }

    override fun get(id: Int): Order {
        return orderRepository.findByIdOrNull(id)?: throw NotFoundException()
    }

}