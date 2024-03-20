package com.spotify.music

import com.spotify.musicdomain.music.Music
import com.spotify.musicdomain.repository.MusicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AdminMusicService(
    private val musicRepository: MusicRepository
) {
    fun createMusics(musics: Flux<Music>) : Flux<Music> {
        return musicRepository.saveAll(musics)
    }
}