package com.spotify.usermusicdomain.service.music

import com.spotify.musicdomain.music.Music
import com.spotify.musicdomain.repository.MusicRepository
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.core.query.TextQuery
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class MusicService(
    private val musicRepository: MusicRepository,
    private val mongoTemplate: ReactiveMongoTemplate
) {
    fun findAllByText(text: String): Flux<Music> {
        val searchText = text.split(" ").toTypedArray()
        val textCriteria = TextCriteria.forDefaultLanguage()
            .matchingAny(*searchText)

        val query = TextQuery.queryText(textCriteria)
            .sortByScore()

        return mongoTemplate.find(query, Music::class.java)
    }
}