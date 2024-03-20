tasks.bootJar { enabled = true }

tasks.jar { enabled = true }

dependencies {
    api("org.springframework.boot:spring-boot-starter-graphql")
    api("com.graphql-java:graphql-java-extended-scalars:19.0")

    testApi("org.springframework.graphql:spring-graphql-test")
}