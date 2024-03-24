package com.spotify.librarydomain.domain.library

import com.spotify.reactivemongomodule.document.BaseTimeDocument


abstract class LibraryNode : BaseTimeDocument {
    var userId: String
        private set

    var type : LibraryNodeType
        private set

    var parentId : String?
        private set

    abstract fun addChild(libraryNode: LibraryNode)

    constructor(id: String, userId: String, type: LibraryNodeType, parentId: String?) : super(id = id) {
        this.userId = userId
        this.type = type
        this.parentId = parentId
    }

    constructor(userId: String, type: LibraryNodeType, parentId: String?) : super() {
        this.userId = userId
        this.type = type
        this.parentId = parentId
    }
}