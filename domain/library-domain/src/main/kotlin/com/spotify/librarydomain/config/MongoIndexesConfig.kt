package com.spotify.librarydomain.config
//
//import jakarta.annotation.PostConstruct
//import org.bson.Document
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate
//import org.springframework.data.mongodb.core.index.CompoundIndexDefinition
//import org.springframework.data.mongodb.core.index.Index
//import org.springframework.data.mongodb.core.index.TextIndexDefinition
//
//@Configuration
//class MongoIndexesConfig(private val mongoTemplate: ReactiveMongoTemplate) {
//    @PostConstruct
//    fun ensureIndexes() {
//        mongoTemplate
//            .indexOps("libraryNode")
//            .ensureIndex(
//                CompoundIndexDefinition(
//                    Document().append("userId", 1).append("_id", 1)
//                )
//            )
//            .subscribe()
//
//        mongoTemplate
//            .indexOps("libraryNode")
//            .ensureIndex(
//                CompoundIndexDefinition(
//                    Document().append("_id", 1).append("userId", 1)
//                )
//            )
//            .subscribe()
//
//        mongoTemplate
//            .indexOps("libraryNode")
//            .ensureIndex(
//                CompoundIndexDefinition(
//                    Document().append("userId", 1).append("parentId", 1)
//                )
//            )
//            .subscribe()
//
//        mongoTemplate
//            .indexOps("libraryNode")
//            .ensureIndex(
//                CompoundIndexDefinition(
//                    Document().append("parentId", 1)
//                )
//            )
//            .subscribe()
//    }
//}