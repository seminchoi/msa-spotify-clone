package com.spotify.librarydomain.domain.library

import com.spotify.librarydomain.domain.library.LibraryNodeType.*
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document
class LibraryPlaylistNode : LibraryNode {
//    var creatorId: String
//    var creatorName: String

    var name: String
        private set

    var children: Set<String>
        private set

    override fun addChild(libraryNode: LibraryNode) {
        if(libraryNode.type != MUSIC) {
            throw RuntimeException()
            TODO("예외 구체화")
        }

        this.children = children.plus(libraryNode.id)
    }

    @PersistenceCreator
    constructor(id: String, userId: String, type: LibraryNodeType, parentId: String?, name: String, children: Set<String> = emptySet())
            : super(id = id, userId = userId, type = type, parentId = parentId) {
        this.name = name
        this.children = children
    }

    constructor(userId: String, parentId: String?, name: String, children: Set<String> = emptySet())
            : super(userId = userId, type = PLAYLIST, parentId = parentId) {
        this.name = name
        this.children = children
    }
}