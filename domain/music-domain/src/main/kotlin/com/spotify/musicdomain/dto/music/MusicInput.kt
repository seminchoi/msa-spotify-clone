package com.spotify.musicdomain.dto.music

import com.spotify.musicdomain.album.Album
import com.spotify.musicdomain.artist.Artist
import com.spotify.musicdomain.music.Genre
import com.spotify.musicdomain.music.Music
import reactor.core.publisher.Flux
import java.time.OffsetDateTime

data class MusicInput(
    val id: String?,
    val name: String,
    val releaseDate: OffsetDateTime,
    val genre: Genre,
    val albumId: String,
    val artistIds: List<String>
) {
    fun toEntity(album: Album, artists: List<Artist>) = Music(
        name = name,
        releaseDate = releaseDate,
        genre = genre,
        album = album,
        artists = artists
    )
}