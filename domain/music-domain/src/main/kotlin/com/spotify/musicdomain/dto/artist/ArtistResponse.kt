package com.spotify.musicdomain.dto.artist

import com.spotify.musicdomain.artist.Artist

data class ArtistResponse(
    val id: String,
    val name: String
) {
    companion object {
        fun from (artist: Artist) = ArtistResponse(
            id = artist.id,
            name = artist.name
        )
    }
}