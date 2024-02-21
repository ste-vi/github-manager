package com.stevi.githubmanager.controller

import com.stevi.githubmanager.payload.request.AddRepoRequest
import com.stevi.githubmanager.payload.response.RepositoryResponse
import com.stevi.githubmanager.service.RepositoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/repos")
class RepositoryController(private val repositoryService: RepositoryService) {

    @GetMapping
    fun getRepositories(): List<RepositoryResponse> {
        return repositoryService.getRepositories("stevi-test")
    }

    @PostMapping
    fun addRepository(@RequestBody request: AddRepoRequest) {
        repositoryService.addRepository("stevi-test", request)
    }
}