package com.spotify.musicdomain.artist

import com.spotify.reactivemongomodule.document.BaseTimeDocument
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Artist : BaseTimeDocument {
    var name: String
        private set

    var introduction: String?
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

    @PersistenceCreator
    constructor(id: String, name:String, introduction: String?, musicIds: Set<String> = emptySet(), albumIds: Set<String> = emptySet()) : super(id = id) {
        this.name = name
        this.introduction = introduction
        this.musicIds = musicIds
        this.albumIds = albumIds
    }

    constructor(name:String, introduction: String? = null) : super() {
        this.name = name
        this.introduction = introduction
    }

}