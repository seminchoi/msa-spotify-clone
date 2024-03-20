package com.spotify.musicdomain.dto.album

import com.spotify.musicdomain.album.Album

data class AlbumResponse(
    val id: String,
    val name: String
) {
    companion object {
        fun from (album: Album) = AlbumResponse(
            id = album.id,
            name = album.name
        )
    }
}