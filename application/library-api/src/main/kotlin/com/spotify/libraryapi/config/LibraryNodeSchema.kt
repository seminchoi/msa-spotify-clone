package com.spotify.libraryapi.config

import com.spotify.librarydomain.domain.library.LibraryNode
import com.spotify.librarydomain.domain.library.LibraryNodeType
import com.spotify.librarydomain.domain.library.LibraryNodeType.*
import com.spotify.librarydomain.dto.LibraryNodeResponse
import graphql.Scalars
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLObjectType.newObject
import graphql.schema.GraphQLSchema
import graphql.schema.TypeResolver
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class LibraryNodeSchema {
@Bean
fun additionalRuntimeWiringConfigure(): RuntimeWiringConfigurer {
    return RuntimeWiringConfigurer { builder ->
        builder.type(newTypeWiring("LibraryNode")
                .typeResolver {
                    when (it.getObject<LibraryNodeResponse>().type) {
                        FOLDER -> it.schema.getObjectType("LibraryFolderNode")
                        PLAYLIST -> it.schema.getObjectType("LibraryPlaylistNode")
                        else -> it.schema.getObjectType("LibraryLeafNode")
                    }
                })
    }
}
}