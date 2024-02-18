package com.stevi.githubmanager.payload.response

data class RepositoryResponse(val id: Long,
                              val name: String,
                              val url: String,
                              val branches: List<String>)
