@file:Suppress("UnstableApiUsage")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/spring-plugin")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "plugins"