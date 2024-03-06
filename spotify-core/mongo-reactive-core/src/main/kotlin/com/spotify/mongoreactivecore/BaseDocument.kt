package com.spotify.mongoreactivecore

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document
abstract class BaseDocument {
    @Id
    var id: Long? = null

    @CreatedDate
    var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    var updatedAt: ZonedDateTime? = null
}
