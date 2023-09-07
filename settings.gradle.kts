pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "EasyRide"
include(":app")
include(":core:ui")
include(":core:data")
include(":feature:onboarding:ui")
include(":feature:authentication:ui")
include(":feature:booking:ui")
