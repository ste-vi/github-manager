package com.stevi.githubmanager.controller

import com.stevi.githubmanager.payload.request.ReleaseRequest
import com.stevi.githubmanager.service.ReleaseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/releases")
class ReleaseController(private val releaseService: ReleaseService) {

    @PostMapping
    fun createRelease(@RequestBody releaseRequest: ReleaseRequest) {
        releaseService.createRelease("stevi-test", releaseRequest)
    }
}