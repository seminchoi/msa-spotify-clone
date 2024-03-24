package com.spotify.librarydomain.dto

import com.spotify.librarydomain.domain.library.*
import com.spotify.librarydomain.domain.library.LibraryNodeType.*

data class LibraryNodeResponse(
    val id: String,
    val type: LibraryNodeType,
    val parentId: String? = null,
    val referenceId: String? = null,
    val name: String? = null,
    val children: List<String>? = null,
    val childPlaylistCount: Int? = null,
    val childFolderCount: Int? = null
) {
    companion object {
        fun from(libraryNode: LibraryNode): LibraryNodeResponse {
            return when(libraryNode.type) {
                FOLDER -> fromFolder(libraryNode)
                PLAYLIST -> fromPlaylist(libraryNode)
                else -> fromLeaf(libraryNode)
            }
        }

        private fun fromFolder(libraryNode: LibraryNode): LibraryNodeResponse {
            val libraryFolderNode =  libraryNode as LibraryFolderNode
            return LibraryNodeResponse(
                id = libraryFolderNode.id,
                type = libraryFolderNode.type,
                parentId = libraryFolderNode.parentId,
                name = libraryFolderNode.name,
                children = libraryFolderNode.children.map { it.id },
                childFolderCount = libraryFolderNode.children.count { it.type == FOLDER },
                childPlaylistCount = libraryFolderNode.children.count() { it.type == PLAYLIST}
            )
        }

        private fun fromPlaylist(libraryNode: LibraryNode): LibraryNodeResponse {
            val libraryPlaylistNode =  libraryNode as LibraryPlaylistNode
            return LibraryNodeResponse(
                id = libraryPlaylistNode.id,
                type = libraryPlaylistNode.type,
                parentId = libraryPlaylistNode.parentId,
                name = libraryPlaylistNode.name,
                children = libraryPlaylistNode.children.toList(),
            )
        }

        private fun fromLeaf(libraryNode: LibraryNode): LibraryNodeResponse {
            val libraryLeafNode =  libraryNode as LibraryLeafNode
            return LibraryNodeResponse(
                id = libraryLeafNode.id,
                type = libraryNode.type,
                parentId = libraryNode.parentId,
                referenceId = libraryLeafNode.referenceId
            )
        }
    }
}