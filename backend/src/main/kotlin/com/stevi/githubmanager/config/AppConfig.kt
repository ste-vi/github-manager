package com.stevi.githubmanager.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.stevi.githubmanager.properties.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class AppConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    //@Bean
    fun defaultObjectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}