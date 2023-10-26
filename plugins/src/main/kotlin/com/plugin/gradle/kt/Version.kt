@file:Suppress("MemberVisibilityCanBePrivate", "unused", "FunctionName")

package com.plugin.gradle.kt

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

object Version {

    val Project.libs: LibrariesForLibs
        get() = extensions.getByType()

    object Json {
        private const val VERSION = "2.10.1"
        const val GSON = "com.google.code.gson:gson:$VERSION"
    }

    object Kotlin {
        private const val COROUTINES_VERSION = "1.7.3"

        val Project.reflect: String
            get() = "org.jetbrains.kotlin:kotlin-reflect:${libs.versions.kotlin.get()}"

        const val COR = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
        const val COR_JVM = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$COROUTINES_VERSION"
        const val COR_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    }

    object Http {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
        const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val OKHTTP_LOG = "com.squareup.okhttp3:logging-interceptor:4.11.0"
    }

}