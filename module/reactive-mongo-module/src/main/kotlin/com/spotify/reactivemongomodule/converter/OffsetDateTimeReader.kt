package com.spotify.reactivemongomodule.converter

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class OffsetDateTimeReader : Converter<Document, OffsetDateTime> {
    override fun convert(document: Document): OffsetDateTime {
        val dateTime: Date = document.getDate(OffsetDateTimeWriter.DATE_FIELD)
        val offset = ZoneOffset.of(document.getString(OffsetDateTimeWriter.OFFSET_FIELD))
        return OffsetDateTime.ofInstant(dateTime.toInstant(), offset)
    }
}