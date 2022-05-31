pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = ("RDCompose")
include (":app")
include(":data")
include(":domain")
include(":core")
include(":navigation:api")
include(":navigation:impl")
include(":shared:ui-model")
include(":shared:ui-widget")
include(":shared:theme")
include(":feature:onboarding")
include(":screen")
include(":screen:main")
include(":feature:user-auth")
include(":screen:home")
include(":screen:categories")
include(":screen:collections")
include(":screen:settings")
include(":screen:user-profile")
include(":screen:podcast-list")
include(":feature:privacy")
