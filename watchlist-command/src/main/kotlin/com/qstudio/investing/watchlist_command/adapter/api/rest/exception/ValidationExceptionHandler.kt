package com.qstudio.investing.watchlist_command.adapter.api.rest.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import java.util.function.Consumer

@ControllerAdvice
class ValidationExceptionHandler {
    @ExceptionHandler(WebExchangeBindException::class)
    fun notValid(ex: WebExchangeBindException): ResponseEntity<*> {
        val errors: MutableList<String?> = ArrayList()
        ex.allErrors.forEach(Consumer { err: ObjectError -> errors.add(err.defaultMessage) })
        val result: MutableMap<String, List<String?>> = HashMap()
        result["errors"] = errors
        return ResponseEntity<Map<String, List<String?>>>(result, HttpStatus.BAD_REQUEST)
    }
}