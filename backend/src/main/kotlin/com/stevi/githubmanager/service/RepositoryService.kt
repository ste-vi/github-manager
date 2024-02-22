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
class RepositoryService(
    private val gitHubApiService: GitHubApiService,
    private val userReposRepository: UserReposRepository
) {

    fun getRepositories(org: String): List<RepositoryResponse> {
        val repoResponseType = object : ParameterizedTypeReference<List<Repository>>() {}
        val branchesResponseType = object : ParameterizedTypeReference<List<Branch>>() {}

        val repos = gitHubApiService.getRequestResponseList("/orgs/$org/repos", repoResponseType)

        val response = repos.map { repo ->
            val branches =
                gitHubApiService.getRequestResponseList("/repos/$org/${repo.name}/branches", branchesResponseType)
            RepositoryResponse(
                repo.id,
                repo.name,
                repo.url,
                branches.map { branch -> branch.name }
            )
        }

        return response;
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

    fun updateRepositoryLocation(newLocationUrl: String, repo: String): String {
        val repo = gitHubApiService.getRequestResponse(newLocationUrl, Repository::class.java)

        return repo.name
    }
}