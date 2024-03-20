tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

dependencies {
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")

    api ("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")

    testApi("io.projectreactor:reactor-test")
}