package com.spotify.adminmusicapi.controller

import com.spotify.musicdomain.dto.album.AlbumInput
import com.spotify.musicdomain.dto.album.AlbumResponse
import com.spotify.adminmusicdomain.album.AdminAlbumService
import com.spotify.musicdomain.album.Album
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class AdminAlbumController(private val adminAlbumService: AdminAlbumService) {
    @MutationMapping
    fun createAlbum(@Argument albumInput: AlbumInput) : Mono<AlbumResponse> {
        return adminAlbumService.createAlbum(albumInput.toEntity())
            .map(AlbumResponse::from)
    }

    @MutationMapping
    fun updateAlbum(@Argument albumInput: AlbumInput) : Mono<AlbumResponse> {
        return adminAlbumService.createAlbum(albumInput.toDocumentWithId())
            .map(AlbumResponse::from)
    }
}