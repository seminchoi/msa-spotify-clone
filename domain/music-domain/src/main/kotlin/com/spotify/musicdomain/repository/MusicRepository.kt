package com.spotify.musicdomain.repository

import com.spotify.musicdomain.music.Music
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MusicRepository : ReactiveMongoRepository<Music, String> {
}
