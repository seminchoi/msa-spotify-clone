package com.spotify.librarydomain.domain.library

import com.spotify.librarydomain.domain.library.LibraryNodeType.*
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.mongodb.core.mapping.Document

@Document
class LibraryFolderNode : LibraryNode {
    var name: String
        private set

    var children: Set<EmbeddableLibraryNode>
        private set

    override fun addChild(libraryNode: LibraryNode) {
        if (libraryNode.type.isLeaf) {
            throw RuntimeException()
            TODO("예외 구체화")
        }

        this.children = children.plus(EmbeddableLibraryNode(libraryNode))
    }

    @PersistenceCreator
    constructor(
        id: String,
        userId: String,
        type: LibraryNodeType,
        parentId: String?,
        name: String,
        children: Set<EmbeddableLibraryNode> = emptySet()
    ) : super(id = id, userId = userId, type = FOLDER, parentId = parentId) {
        if (type != FOLDER) {
            throw RuntimeException()
        }
        this.name = name
        this.children = children
    }

    constructor(
        userId: String,
        parentId: String?,
        name: String,
        children: Set<EmbeddableLibraryNode> = emptySet()
    ) : super(userId = userId, type = FOLDER, parentId = parentId) {
        this.name = name
        this.children = children
    }
}