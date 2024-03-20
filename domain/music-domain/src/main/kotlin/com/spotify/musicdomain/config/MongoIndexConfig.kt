package com.spotify.musicdomain.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.index.TextIndexDefinition

@Configuration
class MongoIndexConfig(private val mongoTemplate: ReactiveMongoTemplate) {
    @PostConstruct
    fun ensureIndexes() {
        mongoTemplate
            .indexOps("music")
            .ensureIndex(
                TextIndexDefinition.TextIndexDefinitionBuilder()
                    .named("music-fts-index")
                    .onField("name", 2F)
                    .onField("lyrics", 1F)
                    .build()
            )
            .subscribe()
    }
}