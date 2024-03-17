package com.spotify.musicdomain.repository

import com.spotify.musicdomain.artist.Artist
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ArtistRepository : ReactiveMongoRepository<Artist, String> {
}
