package com.stevi.githubmanager.service

import com.stevi.githubmanager.entity.User
import com.stevi.githubmanager.entity.UserRepos
import com.stevi.githubmanager.payload.githubapi.Branch
import com.stevi.githubmanager.payload.githubapi.Repository
import com.stevi.githubmanager.payload.request.AddRepoRequest
import com.stevi.githubmanager.payload.response.RepositoryResponse
import com.stevi.githubmanager.repository.UserReposRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class RepositoryService(private val restTemplate: RestTemplate, private val userReposRepository: UserReposRepository) {

    @Value("\${github.api.url}")
    private lateinit var githubApiUrl: String

    @Value("\${github.api.token}")
    private lateinit var githubApiToken: String


    fun getRepositories(org: String): List<RepositoryResponse> {
        val repoResponseType = object : ParameterizedTypeReference<List<Repository>>() {}
        val branchesResponseType = object : ParameterizedTypeReference<List<Branch>>() {}

        val repos = getRequestResponse("/orgs/$org/repos", repoResponseType)

        val response = repos.map { repo ->
            val branches = getRequestResponse("/repos/$org/${repo.name}/branches", branchesResponseType)
            RepositoryResponse(
                repo.id,
                repo.name,
                repo.url,
                branches.map { branch -> branch.name }
            )
        }

        return response;
    }


    private fun <T> getRequestResponse(path: String, responseType: ParameterizedTypeReference<List<T>>): List<T> {
        val apiUrl = "$githubApiUrl$path"
        val headers = HttpHeaders().apply {
            setBearerAuth(githubApiToken)
        }

        val requestEntity = RequestEntity<Any>(headers, HttpMethod.GET, URI.create(apiUrl))
        val responseEntity = restTemplate.exchange(requestEntity, responseType)
        return responseEntity.body?.toList() ?: emptyList()
    }

    fun addRepository(org: String, request: AddRepoRequest) {
        val username = "stevi";

        var userRepos = userReposRepository.findByUserUsername(username);
        if (userRepos == null) {
            userRepos = UserRepos(User(username), listOf(request.repoName));
            userReposRepository.save(userRepos);
        } else {
            userRepos.repos.addLast(request.repoName);
            userReposRepository.save(userRepos);
        }
    }
}