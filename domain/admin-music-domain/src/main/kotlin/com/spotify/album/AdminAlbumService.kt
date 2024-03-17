package com.spotify.album

import com.spotify.musicdomain.album.Album
import com.spotify.musicdomain.music.Music
import com.spotify.musicdomain.repository.AlbumRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AdminAlbumService(
    private val albumRepository: AlbumRepository
) {

    fun createAlbum(album: Album): Mono<Album> {
        return albumRepository.save(album)
    }

    fun findAllByIds(ids: Flux<String>) : Flux<Album> {
        return albumRepository.findAllById(ids)
    }

    fun updateAlbums(musics: Flux<Music>, albumMapMono: Mono<Map<String, Album>>) {
        val albumMusicsMapMono = musics.map { music ->
            Pair(music.album.id, music)
        }.collectMultimap(
            { pair -> pair.first },
            { pair -> pair.second }
        )

        val albumZip = albumMapMono.zipWith(albumMusicsMapMono)

        val albums = albumZip.flatMapMany {
            val albumMap = it.t1
            val albumMusicsMap = it.t2

            val albumIds = Flux.fromIterable(albumMap.keys)

            albumIds.flatMap { albumId ->
                val album = albumMap.getValue(albumId)
                val musics = albumMusicsMap.getValue(albumId)

                album.addMusicIds(musics.map { it.id })
                album.addArtist(musics.flatMap { it.artists })

                Flux.just(album)
            }
        }

        albumRepository.saveAll(albums).subscribe()
    }
}