import com.plugin.gradle.kt.Version

plugins {
    alias(libs.plugins.gradle.jvm)
    application
}

group = "com.ddns.cloudflare"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.ddns.cloudflare.Main")
    applicationName = "ddns"
}

dependencies {
    implementation(Version.Kotlin.cor)
    implementation(Version.Kotlin.cor_jvm)
    implementation(Version.Http.retrofit)
    implementation(Version.Http.retrofit_gson)
}