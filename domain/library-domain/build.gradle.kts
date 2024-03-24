tasks.bootJar { enabled = false }

tasks.jar { enabled = true }

configurations {
    all {
        exclude(group = "org.mongodb", module = "mongo-java-driver")
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    api(project(":module:reactive-mongo-module"))


    api("com.querydsl:querydsl-core:5.0.0")
    api("com.querydsl:querydsl-mongodb:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0")
    kapt("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
}