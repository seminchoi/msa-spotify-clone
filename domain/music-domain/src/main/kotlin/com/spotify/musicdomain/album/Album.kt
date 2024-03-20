package com.spotify.musicdomain.album

import com.spotify.reactivemongomodule.document.BaseTimeDocument
import com.spotify.musicdomain.embeddable.EmbeddableArtist
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@Document
class Album : BaseTimeDocument {
    var name: String
        private set

    var releaseDate: OffsetDateTime
        private set

    var artists: Set<EmbeddableArtist> = emptySet()
        private set

    var musicIds: Set<String> = emptySet()
        private set

    fun addArtist(artists: List<EmbeddableArtist>) {
        this.artists = this.artists.plus(artists)
    }

    fun addMusicIds(musicIds: List<String>) {
        this.musicIds = this.musicIds.plus(musicIds)
    }

    constructor(name: String, releaseDate: OffsetDateTime) : super() {
        this.name = name
        this.releaseDate = releaseDate
    }

    @PersistenceCreator
    constructor(id: String, name: String, releaseDate: OffsetDateTime, artists: Set<EmbeddableArtist> = emptySet(), musicIds: Set<String> = emptySet()
    ) : super(id = id) {
        this.name = name
        this.releaseDate = releaseDate
        this.artists = artists
        this.musicIds = musicIds
    }
}