@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
//        maven { setUrl("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
    }
}

rootProject.name = "ddns"
include(":cloudflare")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")