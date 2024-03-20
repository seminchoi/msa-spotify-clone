package com.spotify.musicdomain.dto.music

import com.spotify.musicdomain.music.Genre
import com.spotify.musicdomain.music.Music
import java.time.OffsetDateTime

data class MusicResponse(
        val id: String,
        val name: String,
        val releaseDate: OffsetDateTime,
        val genre: Genre
) {
    companion object {
        fun from (music: Music) = MusicResponse(
                id = music.id,
                name = music.name,
                releaseDate = music.releaseDate,
                genre = music.genre
        )
    }
}
