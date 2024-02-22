package com.stevi.githubmanager.service

import com.stevi.githubmanager.entity.Release
import com.stevi.githubmanager.entity.ReleasePullRequest
import com.stevi.githubmanager.payload.request.ReleaseRequest
import com.stevi.githubmanager.repository.ReleaseRepository
import org.springframework.stereotype.Service

@Service
class ReleaseService(
    private val pullRequestService: PullRequestService,
    private val releaseRepository: ReleaseRepository
) {

    fun createRelease(org: String, releaseRequest: ReleaseRequest) {
        val releasePullRequests = mutableListOf<ReleasePullRequest>()

        releaseRequest.pullRequestRequests.map { pullRequestRequest ->
            val pullRequest = pullRequestService.createPullRequest(
                org,
                pullRequestRequest
            )

            releasePullRequests.addLast(ReleasePullRequest(pullRequestRequest.repo, pullRequest.number))
        }

        val release = Release(null, releaseRequest.name, null, releasePullRequests)

        releaseRepository.save(release)
    }
}