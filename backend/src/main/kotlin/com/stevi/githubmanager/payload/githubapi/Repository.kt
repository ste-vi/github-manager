package com.stevi.githubmanager.payload.githubapi

import com.fasterxml.jackson.annotation.JsonProperty

data class Repository(
    val id: Long,
    val name: String,
    val url: String,
    @JsonProperty("open_issues_count") val openPRsCount: Int,
    @JsonProperty("default_branch") val defaultBranch: String
)


