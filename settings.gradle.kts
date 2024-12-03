pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidDevs"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:view")
include(":feature:welcome")
include(":feature:login")
include(":core")
