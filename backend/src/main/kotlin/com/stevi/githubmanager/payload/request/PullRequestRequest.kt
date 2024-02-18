package com.stevi.githubmanager.payload.request

data class PullRequestRequest(val title: String,
                              val repo: String,
                              val fromBranch: String,
                              val toBranch: String)
