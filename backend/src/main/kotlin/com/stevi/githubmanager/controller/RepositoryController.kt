package com.stevi.githubmanager.controller

import com.stevi.githubmanager.payload.githubapi.Repository
import com.stevi.githubmanager.payload.response.RepositoryResponse
import com.stevi.githubmanager.service.RepositoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/repos")
class RepositoryController(private val repositoryService: RepositoryService) {

    @GetMapping
    fun getRepositories(): List<RepositoryResponse> {
        return repositoryService.getRepositories("stevi-test")
    }
}