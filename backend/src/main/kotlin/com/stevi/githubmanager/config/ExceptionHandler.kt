package com.stevi.githubmanager.config

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.HttpStatus
import com.stevi.githubmanager.exception.ResourceNotFoundException
import com.stevi.githubmanager.payload.response.ApiResponse

@ControllerAdvice
class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseBody
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ApiResponse {
        return ApiResponse(ex.message!!)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(ex: Exception): ApiResponse {
        return ApiResponse("Something went wrong")
    }
}