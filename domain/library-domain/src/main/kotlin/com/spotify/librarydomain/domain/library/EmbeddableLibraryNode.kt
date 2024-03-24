package com.spotify.librarydomain.domain.library

import com.spotify.reactivemongomodule.document.BaseDocument
import org.springframework.data.annotation.PersistenceCreator

class EmbeddableLibraryNode : BaseDocument {
    var type: LibraryNodeType
        private set

    @PersistenceCreator
    constructor(id: String, type: LibraryNodeType) : super(id = id) {
        this.type = type
    }

    constructor(libraryNode: LibraryNode) : super(id = libraryNode.id) {
        this.type = libraryNode.type
    }
}