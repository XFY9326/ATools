pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "ATools"

include(":app")

include(":base")

include(":core")
include(":io")
include(":ui")
include(":compose")

include(":crash")
include(":coroutines")
include(":livedata")
include(":datastore-preference")
include(":datastore-preference-adapter")
include(":io-serialization-json")
