tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

dependencies {
    api(project(":domain:music-domain"))
}