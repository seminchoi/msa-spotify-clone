tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

dependencies {
    api(project(":module:reactive-mongo-module"))
}