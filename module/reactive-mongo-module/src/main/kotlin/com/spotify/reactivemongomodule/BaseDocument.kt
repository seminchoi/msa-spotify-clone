package com.spotify.reactivemongomodule

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

abstract class BaseDocument(
    id: String = ObjectId().toHexString()
) {
    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    var id = id
        private set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseDocument) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}