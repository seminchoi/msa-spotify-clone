package com.spotify.musicdomain.music

import com.spotify.reactivemongomodule.document.BaseTimeDocument
import com.spotify.musicdomain.album.Album
import com.spotify.musicdomain.artist.Artist
import com.spotify.musicdomain.embeddable.EmbeddableAlbum
import com.spotify.musicdomain.embeddable.EmbeddableArtist
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@Document
class Music : BaseTimeDocument {
    var name: String
        private set

    var releaseDate: OffsetDateTime
        private set

    var genre: Genre
        private set

    var lyrics: String?
        private set

    var album: EmbeddableAlbum
        private set

    var artists: Set<EmbeddableArtist>
        private set

    constructor(name: String, releaseDate: OffsetDateTime, genre: Genre, lyrics: String? = null, album: Album, artists: List<Artist>) {
        this.name = name
        this.releaseDate = releaseDate
        this.genre = genre
        this.lyrics = lyrics
        this.album = EmbeddableAlbum(album)
        this.artists = artists.map(::EmbeddableArtist).toSet()
    }

    @PersistenceCreator
    constructor(id: String, name: String, releaseDate: OffsetDateTime, genre: Genre, lyrics: String? = null, album: EmbeddableAlbum, artists: Set<EmbeddableArtist>) : super(id = id) {
        this.name = name
        this.releaseDate = releaseDate
        this.genre = genre
        this.lyrics = lyrics
        this.album = album
        this.artists = artists
    }
}
