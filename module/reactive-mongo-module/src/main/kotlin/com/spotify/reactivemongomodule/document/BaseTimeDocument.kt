package com.spotify.reactivemongomodule.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.OffsetDateTime

abstract class BaseTimeDocument: BaseDocument {
    constructor() : super()
    constructor(id: String) : super(id)

    @CreatedDate
    var createdAt: OffsetDateTime = OffsetDateTime.now()
        private set

    @LastModifiedDate
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
        private set
}