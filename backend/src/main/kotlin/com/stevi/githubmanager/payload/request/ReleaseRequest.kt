package com.stevi.githubmanager.payload.request

data class ReleaseRequest(val name: String,
                          val pullRequestRequests: List<PullRequestRequest>)
