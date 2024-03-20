package com.spotify.musicdomain.embeddable

import com.spotify.reactivemongomodule.document.BaseDocument
import com.spotify.musicdomain.artist.Artist
import org.springframework.data.annotation.PersistenceCreator

class EmbeddableArtist : BaseDocument {
    var name: String
        private set

    @PersistenceCreator
    constructor(id: String, name: String) : super(id = id) {
        this.name = name
    }

    constructor(artist: Artist) : super(id = artist.id) {
        this.name = artist.name
    }
}