package com.spotify.musicdomain.artist

import com.spotify.reactivemongomodule.document.BaseTimeDocument
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Artist(
    name: String,
    introduction: String? = null,
) : BaseTimeDocument() {
    var name = name
        private set

    var introduction = introduction
        private set

    var musicIds: Set<String> = emptySet()
        private set

    var albumIds: Set<String> = emptySet()
        private set


    fun addMusics(musicIds: List<String>) {
        this.musicIds = this.musicIds.plus(musicIds)
    }

    fun addAlbums(albumIds: List<String>) {
        this.albumIds = this.albumIds.plus(albumIds)
    }
}