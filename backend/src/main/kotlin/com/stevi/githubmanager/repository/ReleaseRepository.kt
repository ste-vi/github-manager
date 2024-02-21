package com.stevi.githubmanager.repository

import com.stevi.githubmanager.entity.Release
import com.stevi.githubmanager.entity.UserRepos
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ReleaseRepository : MongoRepository<Release, String> {

}