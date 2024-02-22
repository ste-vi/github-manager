package com.stevi.githubmanager.service

import com.stevi.githubmanager.exception.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class GitHubApiService(private val restTemplate: RestTemplate) {

    @Value("\${github.api.url}")
    private lateinit var githubApiUrl: String

    @Value("\${github.api.token}")
    private lateinit var githubApiToken: String

    fun <T> getRequestResponse(apiUrl: String, responseType: Class<T>): T {
        val headers = HttpHeaders().apply {
            setBearerAuth(githubApiToken)
        }

        val requestEntity = RequestEntity<Any>(headers, HttpMethod.GET, URI.create(apiUrl))
        val responseEntity = restTemplate.exchange(requestEntity, responseType)
        return responseEntity.body ?: throw ResourceNotFoundException("Resource not found")
    }

    public fun <T> getRequestResponseList(path: String, responseType: ParameterizedTypeReference<List<T>>): List<T> {
        val apiUrl = "$githubApiUrl$path"
        val headers = HttpHeaders().apply {
            setBearerAuth(githubApiToken)
        }

        val requestEntity = RequestEntity<Any>(headers, HttpMethod.GET, URI.create(apiUrl))
        val responseEntity = restTemplate.exchange(requestEntity, responseType)
        return responseEntity.body?.toList() ?: emptyList()
    }
}