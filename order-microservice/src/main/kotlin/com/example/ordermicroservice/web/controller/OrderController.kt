package com.example.ordermicroservice.web.controller

import com.example.ordermicroservice.data.entity.Order
import com.example.ordermicroservice.data.entity.Station
import com.example.ordermicroservice.domain.model.NotFoundException
import com.example.ordermicroservice.domain.service.OrderService
import com.example.ordermicroservice.domain.service.StationService
import com.example.ordermicroservice.domain.service.UpdateService
import com.example.ordermicroservice.web.dto.*
import io.swagger.v3.oas.annotations.Parameter
import jakarta.security.auth.message.AuthException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-app/order")
@Validated
class OrderController(
    private val orderService: OrderService,
    private val updateService: UpdateService,
    private val stationService: StationService
) {

    @PostMapping
    fun create(
        @Parameter(hidden = true)
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody request: OrderDto
    ): ResponseEntity<Order> {
        val order = orderService.create(request, token)
        updateService.addToQueue(order)
        return ResponseEntity.ok(order)
    }


    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Order> {
        return ResponseEntity.ok(orderService.get(id))
    }

    @PostMapping("/station")
    fun create(@RequestParam station: String): ResponseEntity<Station> {
        return ResponseEntity.ok(stationService.create(station))
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFound(e: NotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @ExceptionHandler(AuthException::class)
    fun authentication(e: AuthException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val map = mutableMapOf<String, String>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val message = error.defaultMessage
            if (message != null) {
                map[fieldName] = message
            }
        }
        return ResponseEntity.badRequest().body(map)
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    fun missingRequestHeader(e: MissingRequestHeaderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun other(e: Exception) : ResponseEntity<String> {
        return ResponseEntity.internalServerError().body(e.message)
    }
}
