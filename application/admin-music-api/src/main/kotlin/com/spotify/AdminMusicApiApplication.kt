package com.spotify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AdminMusicApiApplication

fun main(args: Array<String>) {
    runApplication<AdminMusicApiApplication>(*args)
}
