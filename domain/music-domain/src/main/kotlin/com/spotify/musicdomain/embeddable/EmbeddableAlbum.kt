package com.spotify.musicdomain.embeddable

import com.spotify.reactivemongomodule.document.BaseDocument
import com.spotify.musicdomain.album.Album
import org.springframework.data.annotation.PersistenceCreator

class EmbeddableAlbum : BaseDocument {
    var name: String
        private set

    @PersistenceCreator
    constructor(id: String, name: String) : super(id = id) {
        this.name = name
    }

    constructor(album: Album) : super(id = album.id) {
        this.name = album.name
    }
}