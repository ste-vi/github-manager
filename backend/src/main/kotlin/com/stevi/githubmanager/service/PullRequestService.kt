package com.stevi.githubmanager.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.stevi.githubmanager.payload.githubapi.PullRequest
import com.stevi.githubmanager.payload.request.PullRequestRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class PullRequestService(
    private val repositoryService: RepositoryService,
    private val restTemplate: RestTemplate,
    private val defaultObjectMapper: ObjectMapper
) {

    @Value("\${github.api.url}")
    private lateinit var githubApiUrl: String

    @Value("\${github.api.token}")
    private lateinit var githubApiToken: String

    fun createPullRequest(org: String, pullRequestRequest: PullRequestRequest): PullRequest {
        var responseEntity = callCreatePRApi(org, pullRequestRequest.repo, pullRequestRequest)

        if (responseEntity.statusCode.value() == 307) {
            responseEntity.headers.getFirst("Location")
                ?.let {
                    val newRepoName = repositoryService.updateRepositoryLocation(it.replace("/pulls", ""), pullRequestRequest.repo)
                    responseEntity = callCreatePRApi(org, newRepoName, pullRequestRequest)
                }
        }

        val body = responseEntity.body ?: throw IllegalStateException("Response body is null")

        return defaultObjectMapper.readValue(body, PullRequest::class.java)
    }

    private fun callCreatePRApi(
        org: String,
        repo: String,
        pullRequestRequest: PullRequestRequest
    ): ResponseEntity<String> {
        val path = "/repos/$org/$repo/pulls"
        val apiUrl = "$githubApiUrl$path"
        val headers = HttpHeaders().apply {
            setBearerAuth(githubApiToken)
        }

        val requestBody = mapOf(
            "title" to pullRequestRequest.title,
            "head" to pullRequestRequest.fromBranch,
            "base" to pullRequestRequest.toBranch,
            "body" to "Auto created"
        )

        val requestEntity = HttpEntity(requestBody, headers)

        val responseEntity = restTemplate.exchange(
            URI(apiUrl),
            HttpMethod.POST,
            requestEntity,
            String::class.java
        )
        return responseEntity
    }
}