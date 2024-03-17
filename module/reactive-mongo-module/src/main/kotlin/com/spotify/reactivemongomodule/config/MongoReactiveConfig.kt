package com.spotify.reactivemongomodule.config

import com.spotify.reactivemongomodule.converter.OffsetDateTimeReader
import com.spotify.reactivemongomodule.converter.OffsetDateTimeWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.spotify.***.repository"])
class MongoReactiveConfig {
    @Bean
    fun reactiveTransactionManager(reactiveMongoDatabaseFactory: ReactiveMongoDatabaseFactory): ReactiveTransactionManager {
        return ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory)
    }

    @Bean
    fun transactionalOperator(reactiveTransactionManager: ReactiveTransactionManager): TransactionalOperator {
        return TransactionalOperator.create(reactiveTransactionManager)
    }

    @Bean
    fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions(
            listOf<Any?>(
                OffsetDateTimeWriter(),
                OffsetDateTimeReader()
            )
        )
    }
}