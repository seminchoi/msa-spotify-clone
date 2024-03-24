package com.spotify.librarydomain.service

import com.spotify.librarydomain.domain.library.*
import com.spotify.librarydomain.domain.library.LibraryNodeType.*
import com.spotify.librarydomain.dto.LibraryNodeResponse
import com.spotify.librarydomain.repository.LibraryRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class LibraryService(
    private val libraryRepository: LibraryRepository
) {
    fun createLibraryNode(libraryNode: LibraryNode): Mono<LibraryNode> {
        updateChildrenOfParent(libraryNode)
        return libraryRepository.save(libraryNode)
    }

    fun updateChildrenOfParent(libraryNode: LibraryNode) {
        if (libraryNode.parentId == null)
            return

        val parentNode = libraryRepository.findById(libraryNode.parentId!!)
        parentNode.flatMap {
            it.addChild(libraryNode)
            libraryRepository.save(it)
        }.subscribe()
    }


fun goBack(parentIdOfCurNodes: String): Flux<LibraryNode> {
    val parentIdOfParentNode = libraryRepository
        .findById(parentIdOfCurNodes)
        .switchIfEmpty(Mono.error(RuntimeException()))

    return parentIdOfParentNode.flatMapMany { findChildren(it.parentId) }
}

    fun findChildren(parentId: String?, userId: String = "2"): Flux<LibraryNode> {
        return when {
            parentId == null -> libraryRepository.findByParentIdIsNullAndUserId(userId)
            else -> libraryRepository.findAllByParentId(parentId)
        }
    }
}