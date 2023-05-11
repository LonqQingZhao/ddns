plugins {
    `kotlin-dsl`
}

allprojects {
    buildDir = File(File(File(rootDir.parent, "build"), "plugins"), name)
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(gradleApi())
}