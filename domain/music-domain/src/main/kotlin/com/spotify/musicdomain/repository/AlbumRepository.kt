package com.spotify.musicdomain.repository

import com.spotify.musicdomain.album.Album
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface AlbumRepository : ReactiveMongoRepository<Album, String> {
}
