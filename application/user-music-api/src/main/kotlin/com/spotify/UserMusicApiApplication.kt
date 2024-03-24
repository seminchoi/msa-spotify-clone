package com.spotify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserMusicApiApplication

fun main(args: Array<String>) {
    runApplication<UserMusicApiApplication>(*args)
}
