package com.spotify.adminmusicapi.controller

import com.spotify.musicdomain.dto.artist.ArtistInput
import com.spotify.musicdomain.dto.artist.ArtistResponse
import com.spotify.adminmusicdomain.artist.AdminArtistService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class AdminArtistController(
    private val adminArtistService: AdminArtistService
) {
    @MutationMapping
    fun createArtist(@Argument artistInput: ArtistInput): Mono<ArtistResponse> {
        val artistMono = adminArtistService.createArtist(artistInput.toEntity())
        return artistMono.map(ArtistResponse::from)
    }
}