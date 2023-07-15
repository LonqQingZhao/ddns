@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("plugins")
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/spring-plugin")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        mavenLocal()
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
    }
}

rootProject.name = "ddns"
include(":cloudflare")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")