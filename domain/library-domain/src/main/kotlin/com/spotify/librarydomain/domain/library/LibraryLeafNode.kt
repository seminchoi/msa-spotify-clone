package com.spotify.librarydomain.domain.library

import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document
class LibraryLeafNode : LibraryNode {
    var referenceId: String
        private set

    override fun addChild(libraryNode: LibraryNode) {
        throw RuntimeException("리프노드에는 child를 추가할 수 없습니다.")
        TODO("예외 구체화")
    }

    @PersistenceCreator
    constructor(id: String, userId: String, type: LibraryNodeType, parentId: String? = null, referenceId: String)
            : super(id = id, userId = userId, type = type, parentId = parentId) {
        this.referenceId = referenceId
    }

    constructor(userId: String, type: LibraryNodeType, parentId: String? = null, referenceId: String)
            : super(userId = userId, type = type, parentId = parentId) {
        validate(type, parentId)
        this.referenceId = referenceId
    }

    private fun validate(type: LibraryNodeType, parentId: String?) {
        if (type == LibraryNodeType.MUSIC) {
            if (parentId == null) {
                throw RuntimeException()
            }
        }
        if (type == LibraryNodeType.ALBUM || type == LibraryNodeType.ARTIST) {
            if (parentId != null) {
                throw RuntimeException()
            }
        }
    }
}