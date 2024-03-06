package com.spotify.mongoreactivemodule

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.ZonedDateTime

@Document
abstract class BaseDocument {
    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    val id: String = ObjectId().toHexString()

    @CreatedDate
    var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    var updatedAt: ZonedDateTime? = null
}