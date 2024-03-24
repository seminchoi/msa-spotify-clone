package com.spotify.librarydomain.repository

import com.spotify.librarydomain.domain.library.LibraryNode
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface LibraryRepository: ReactiveMongoRepository<LibraryNode, String> {
    fun findAllByParentId(parentId: String): Flux<LibraryNode>
    fun findByParentIdIsNullAndUserId(userId: String): Flux<LibraryNode>
}