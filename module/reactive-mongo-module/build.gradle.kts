tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
}