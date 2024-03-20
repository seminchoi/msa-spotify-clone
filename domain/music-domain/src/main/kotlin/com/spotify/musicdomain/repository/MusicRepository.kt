package com.spotify.musicdomain.repository

import com.spotify.musicdomain.music.Music
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface MusicRepository : ReactiveMongoRepository<Music, String> {
    fun findAllBy(textCriteria: TextCriteria): Flux<Music>
}
