rootProject.name = "spotify"

// module
include("module:reactive-mongo-module")
include("module:webflux-module")
include("module:graphql-module")

// domain
include("domain:music-domain")
include("domain:admin-music-domain")
include("domain:user-music-domain")
include("domain:library-domain")

// application
include("application:user-music-api")
include("application:admin-music-api")
include("application:library-api")



pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}