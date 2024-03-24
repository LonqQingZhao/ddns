import com.plugin.gradle.kt.Version

plugins {
    alias(libs.plugins.gradle.jvm)
    application
}

group = "com.ddns.cloudflare"
version = "1.1"

application {
    mainClass.set("com.ddns.cloudflare.Main")
    applicationName = "ddns"
//    mainModule.set("ddns")
}

dependencies {
    implementation(Version.Kotlin.COR)
    implementation(Version.Kotlin.COR_JVM)
    implementation(Version.Http.RETROFIT)
    implementation(Version.Http.RETROFIT_GSON)
    implementation(Version.Log.LOG4J_API)
    implementation(Version.Log.LOG4J_CORE)
    implementation(Version.Log.LOG4J_IMPL)
}