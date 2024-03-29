package com.stevi.githubmanager.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("releases")
data class Release(
    @Id val id: ObjectId? = ObjectId(),
    val name: String,
    val organization: Organization? = null,
    val pullRequests: List<ReleasePullRequest>
)

data class Organization(
    val id: ObjectId = ObjectId(),
    @Field("name") val name: String = "stevi-test"
)

data class ReleasePullRequest(
    @Field("name") val repo: String,
    @Field("pr_no") val prNo: Int
)