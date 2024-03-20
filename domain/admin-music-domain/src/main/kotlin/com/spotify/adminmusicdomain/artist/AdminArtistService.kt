package com.spotify.artist

import com.spotify.musicdomain.artist.Artist
import com.spotify.musicdomain.music.Music
import com.spotify.musicdomain.repository.ArtistRepository
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AdminArtistService(
    private val artistRepository: ArtistRepository,
) {
    fun createArtist(artist: Artist): Mono<Artist> {
        return artistRepository.save(artist)
    }

    fun findAllByIds(ids: Flux<String>): Flux<Artist> {
        return artistRepository.findAllById(ids)
    }

    fun updateArtists(musics: Flux<Music>, artistMapMono: Mono<Map<String, Artist>>) {
        val artistMusicsMapMono = musics.flatMap { music ->
            Flux.fromIterable(music.artists.map { artist ->
                Pair(artist.id, music)
            })
        }.collectMultimap(
            { pair -> pair.first },
            { pair -> pair.second }
        )

        val artistZip = artistMapMono.zipWith(artistMusicsMapMono)

        val artists = artistZip.flatMapMany { zip ->
            val artistMap = zip.t1
            val artistMusicsMap = zip.t2

            val artistIds = Flux.fromIterable(artistMap.keys)
            artistIds.map { artistId ->
                val artist = artistMap.getValue(artistId)
                val musics = artistMusicsMap.getValue(artistId)

                artist.addMusics(musics.map { it.id })
                artist.addAlbums(musics.flatMap { it.artists.map { it.id } })

                artist
            }
        }

        artistRepository.saveAll(artists).subscribe()
    }
}