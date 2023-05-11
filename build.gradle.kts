tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("JavaCompile", JavaCompile::class) {
    this.options.encoding = "UTF-8"
}

allprojects {
    buildDir = File(rootProject.buildDir, name)
}

plugins {
    id("com.plugin.gradle.total") apply false
    alias(libs.plugins.gradle.jvm) apply false
}