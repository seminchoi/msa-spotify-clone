package com.spotify.musicdomain.dto.album

import com.spotify.musicdomain.album.Album
import java.time.OffsetDateTime
import java.time.ZonedDateTime

data class AlbumInput(
    val id: String?,
    val name: String,
    val releaseDate: OffsetDateTime,
) {
    fun toEntity() = Album(
        name = name,
        releaseDate = releaseDate
    )

    fun toDocumentWithId() = Album(
        id = id!!,
        name = name,
        releaseDate = releaseDate
    )
}