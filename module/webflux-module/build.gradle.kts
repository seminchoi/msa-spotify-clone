tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    implementation ("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")

    testImplementation("io.projectreactor:reactor-test")
}