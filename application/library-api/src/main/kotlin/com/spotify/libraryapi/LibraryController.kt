package com.spotify.libraryapi

import com.spotify.librarydomain.dto.LibraryNodeInput
import com.spotify.librarydomain.dto.LibraryNodeResponse
import com.spotify.librarydomain.service.LibraryService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class LibraryController(private val libraryService: LibraryService) {
    @MutationMapping
    fun createLibraryNode(@Argument libraryNodeInput: LibraryNodeInput): Mono<LibraryNodeResponse> {
        return libraryService.createLibraryNode(libraryNodeInput.toDocument("2"))
            .map(LibraryNodeResponse::from)
    }

@QueryMapping
fun getChildren(@Argument id: String?): Flux<LibraryNodeResponse> {
    return libraryService.findChildren(id)
        .map(LibraryNodeResponse::from)
}

@QueryMapping
fun goBack(@Argument parentIdOfCurNodes: String): Flux<LibraryNodeResponse> {
    return libraryService.goBack(parentIdOfCurNodes)
        .map(LibraryNodeResponse::from)
}
}