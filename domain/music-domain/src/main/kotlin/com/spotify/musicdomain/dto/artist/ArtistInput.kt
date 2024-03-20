package com.spotify.musicdomain.dto.artist

import com.spotify.musicdomain.artist.Artist

data class ArtistInput(
    val id: String? = null,
    val name: String
) {
    fun toEntity() = Artist(
        name = name
    )
}