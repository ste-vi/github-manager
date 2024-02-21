package com.stevi.githubmanager.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document("user_repos")
data class UserRepos(val user: User, val repos: List<String>) {
}