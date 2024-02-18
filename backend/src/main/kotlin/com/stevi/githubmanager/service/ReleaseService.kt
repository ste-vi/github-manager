package com.stevi.githubmanager.service

import com.stevi.githubmanager.payload.request.ReleaseRequest
import org.springframework.stereotype.Service

@Service
class ReleaseService(private val pullRequestService: PullRequestService) {

    fun createRelease(org: String, releaseRequest: ReleaseRequest) {
        releaseRequest.pullRequestRequests.forEach { pullRequestRequest ->
            val pullRequest = pullRequestService.createPullRequest(
                org,
                pullRequestRequest
            )
            pullRequest?.state;
        }
    }
}