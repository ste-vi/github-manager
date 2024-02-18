package com.stevi.githubmanager.payload.githubapi

import com.fasterxml.jackson.annotation.JsonProperty

data class PullRequest(
    val id: Long,
    val title: String,
    val state: String,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("updated_at") val updatedAt: String,
    val url: String,
    val merged: Boolean,
    val commits: Int
)
