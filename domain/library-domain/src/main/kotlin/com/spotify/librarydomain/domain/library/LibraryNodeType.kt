package com.spotify.librarydomain.domain.library

enum class LibraryNodeType(val isLeaf: Boolean) {
    MUSIC(true),
    ALBUM(true),
    ARTIST(true),
    PLAYLIST_REFERENCE(true),
    PLAYLIST(false),
    FOLDER(false)
}