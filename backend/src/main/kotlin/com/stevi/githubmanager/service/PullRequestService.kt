package com.stevi.githubmanager.service

import com.stevi.githubmanager.payload.githubapi.PullRequest
import com.stevi.githubmanager.payload.request.PullRequestRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class PullRequestService(private val restTemplate: RestTemplate) {

    @Value("\${github.api.url}")
    private lateinit var githubApiUrl: String

    @Value("\${github.api.token}")
    private lateinit var githubApiToken: String

    fun createPullRequest(org: String, pullRequestRequest: PullRequestRequest): PullRequest? {
        val path = "/repos/$org/${pullRequestRequest.repo}/pulls"
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

        val responseType = object : ParameterizedTypeReference<PullRequest>() {}

        val responseEntity = restTemplate.exchange(
            URI(apiUrl),
            HttpMethod.POST,
            requestEntity,
            responseType
        )

        return responseEntity.body;
    }
}