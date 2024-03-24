package com.spotify.librarydomain.dto

import com.spotify.librarydomain.domain.library.*
import com.spotify.librarydomain.domain.library.LibraryNodeType.*


data class LibraryNodeInput(
    val type: LibraryNodeType,
    val referenceId: String?,
    val parentId: String?,
    val name: String?
) {
    fun toDocument(userId: String): LibraryNode {
        return when (type) {
            FOLDER -> toFolder(userId)
            PLAYLIST -> toPlaylist(userId)
            else -> toLeaf(userId)
        }
    }

    private fun toFolder(userId: String): LibraryNode {
        if (referenceId != null || name == null) {
            throw RuntimeException()
        }
        return LibraryFolderNode(userId = userId, parentId = parentId, name = name)
    }

    private fun toPlaylist(userId: String): LibraryNode {
        if (referenceId != null || name == null) {
            throw RuntimeException()
        }
        return LibraryPlaylistNode(userId = userId, parentId = parentId, name = name)
    }

    private fun toLeaf(userId: String): LibraryNode {
        if(name != null || referenceId == null) {
            throw RuntimeException()
        }
        return LibraryLeafNode(userId = userId, type = type, parentId = parentId, referenceId = referenceId)
    }
}
