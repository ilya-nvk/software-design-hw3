package com.example.authmicroservice.web.controller


import com.example.authmicroservice.data.entity.User
import com.example.authmicroservice.domain.exception.NotFoundException
import com.example.authmicroservice.domain.service.LoginService
import com.example.authmicroservice.domain.service.UserService
import com.example.authmicroservice.web.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.*

@RestController
@RestControllerAdvice
@RequestMapping("/auth-app/user")
@Validated
@Tag(name = "User Controller", description = "API for user management and authentication")
class UserController(
    private val userService: UserService,
    private val loginService: LoginService
) {
    @Operation
    @PostMapping("/register")
    fun register(@Valid @RequestBody dto: UserDto): ResponseEntity<User> {
        return ResponseEntity.ok(userService.create(dto))
    }

    @Operation
    @PostMapping("/login")
    fun login(@Valid @RequestBody auth: AuthDto): ResponseEntity<TokenWrapper> {
        val token = loginService.login(auth)
        return ResponseEntity.ok(TokenWrapper(token))
    }

    @Operation
    @GetMapping
    fun get(): ResponseEntity<User> {
        val email = SecurityContextHolder.getContext().authentication.name
        return ResponseEntity.ok(userService.getByEmail(email))
    }

    @Operation(hidden = true)
    @ExceptionHandler(NotFoundException::class)
    fun notFound(e: NotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
    }

    @Operation(hidden = true)
    @ExceptionHandler(AuthenticationException::class)
    fun authentication(e: AuthenticationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
    }

    @Operation(hidden = true)
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

    @Operation(hidden = true)
    @ExceptionHandler(MissingRequestHeaderException::class)
    fun missingRequestHeader(e: MissingRequestHeaderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @Operation(hidden = true)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @Operation(hidden = true)
    @ExceptionHandler(Exception::class)
    fun other(e: Exception) : ResponseEntity<String> {
        return ResponseEntity.internalServerError().body(e.message)
    }
}


