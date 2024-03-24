package com.spotify.usermusicapi

import com.spotify.musicdomain.dto.music.MusicResponse
import com.spotify.usermusicdomain.service.music.MusicService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class MusicController(private val musicService: MusicService) {
    @QueryMapping
    fun searchMusics(@Argument searchText: String): Flux<MusicResponse> {
        return musicService.findAllByText(searchText).map(MusicResponse::from)
    }
}