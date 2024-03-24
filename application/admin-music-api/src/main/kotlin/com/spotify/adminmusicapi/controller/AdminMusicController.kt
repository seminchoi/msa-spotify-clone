package com.spotify.adminmusicapi.controller

import com.spotify.musicdomain.dto.music.MusicInput
import com.spotify.musicdomain.dto.music.MusicResponse
import com.spotify.adminmusicdomain.album.AdminAlbumService
import com.spotify.adminmusicdomain.artist.AdminArtistService
import com.spotify.adminmusicdomain.music.AdminMusicService
import com.spotify.musicdomain.album.Album
import com.spotify.musicdomain.artist.Artist
import com.spotify.musicdomain.music.Music
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.GroupedFlux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import java.util.function.Function

@Controller
class AdminMusicController(
    private val musicService: AdminMusicService,
    private val albumService: AdminAlbumService,
    private val artistService: AdminArtistService
) {
    @MutationMapping
    fun createMusics(@Argument musicInputs: List<MusicInput>): Flux<MusicResponse> {
        val musicInputs = Flux.fromIterable(musicInputs)
        val albumMap = createAlbumMap(musicInputs)
        val artistMap = createArtistMap(musicInputs)
        val albumAndArtistMapZip = albumMap.zipWith(artistMap)
        val musics = convertMusic(musicInputs, albumAndArtistMapZip)

        artistService.updateArtists(musics, artistMap)
        albumService.updateAlbums(musics, albumMap)

        return musicService.createMusics(musics)
            .map(MusicResponse::from)
    }

    private fun createAlbumMap(musicInputs: Flux<MusicInput>): Mono<Map<String, Album>> {
        return albumService.findAllByIds(
            musicInputs.groupBy(MusicInput::albumId).map(GroupedFlux<String, MusicInput>::key)
        ).collectMap(Album::id, Function.identity())
    }

    private fun createArtistMap(musicInputs: Flux<MusicInput>): Mono<Map<String, Artist>> {
        return artistService.findAllByIds(
            musicInputs
                .flatMap { musicInput -> Flux.fromIterable(musicInput.artistIds) }
                .distinct()
        ).collectMap(Artist::id, Function.identity())
    }

    private fun convertMusic(
        musicInputs: Flux<MusicInput>,
        albumAndArtistMapZip: Mono<Tuple2<Map<String, Album>, Map<String, Artist>>>
    ): Flux<Music> {
        return albumAndArtistMapZip.flatMapMany { albumArtistZip ->
            musicInputs.map { musicInput ->
                val album = albumArtistZip.t1.getValue(musicInput.albumId)
                val artists = ArrayList<Artist>()
                musicInput.artistIds.map {
                    artists.add(albumArtistZip.t2.getValue(it))
                }
                musicInput.toEntity(album, artists)
            }
        }
    }
}