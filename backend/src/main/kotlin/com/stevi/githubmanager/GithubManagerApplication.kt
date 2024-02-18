package com.stevi.githubmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GithubManagerApplication

fun main(args: Array<String>) {
	runApplication<GithubManagerApplication>(*args)
}
