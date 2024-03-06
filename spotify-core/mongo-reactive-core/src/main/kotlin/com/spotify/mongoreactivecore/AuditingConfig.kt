package com.spotify.mongoreactivecore

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import java.time.ZonedDateTime
import java.util.*

@Configuration
@EnableReactiveMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
class AuditingConfig {
    @Bean
    fun auditingDateTimeProvider() = DateTimeProvider { Optional.of(ZonedDateTime.now()) }
}