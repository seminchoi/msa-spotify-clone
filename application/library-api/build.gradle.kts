tasks.bootJar { enabled = true }

tasks.jar { enabled = true }



dependencies {
    implementation(project(":module:webflux-module"))
    implementation(project(":module:graphql-module"))

    implementation(project(":domain:library-domain"))
}

