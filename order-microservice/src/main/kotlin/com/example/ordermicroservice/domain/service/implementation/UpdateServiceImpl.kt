package com.example.ordermicroservice.domain.service.implementation

import com.example.ordermicroservice.data.entity.Order
import com.example.ordermicroservice.data.entity.Status
import com.example.ordermicroservice.domain.service.OrderService
import com.example.ordermicroservice.domain.service.UpdateService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Service
class UpdateServiceImpl(
    private val orderService: OrderService,
) : UpdateService {
    companion object {
        val blockingQueue: BlockingQueue<Int> = ArrayBlockingQueue(5)
    }

    override fun addToQueue(order: Order) {
        while (!blockingQueue.add(order.id)){}
    }

    @Scheduled(fixedRate = 10000)
    fun handleOrder() {
        if (blockingQueue.isEmpty()) return
        try {
            val orderId = blockingQueue.take()
            orderService.updateStatus(orderId)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}