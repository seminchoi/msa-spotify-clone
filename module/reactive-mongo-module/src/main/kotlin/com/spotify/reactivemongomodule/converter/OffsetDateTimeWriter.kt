package com.spotify.reactivemongomodule.converter

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.util.*

class OffsetDateTimeWriter : Converter<OffsetDateTime, Document> {
    companion object {
        const val DATE_FIELD: String = "dateTime"
        const val OFFSET_FIELD: String = "offset"
    }

    override fun convert(offsetDateTime: OffsetDateTime): Document {
        val document = Document()
        document[DATE_FIELD] = Date.from(offsetDateTime.toInstant())
        document[OFFSET_FIELD] = offsetDateTime.offset.toString()
        return document
    }
}
