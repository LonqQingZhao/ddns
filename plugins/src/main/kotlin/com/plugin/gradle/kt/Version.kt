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
        const val gson = "com.google.code.gson:gson:$VERSION"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.7.0"
    }

    object Rtc {
        const val webrtc = "org.webrtc:google-webrtc:1.0.28513"
    }

    object Kotlin {
        private const val COROUTINES_VERSION = "1.7.0"

        val Project.reflect: String
            get() = "org.jetbrains.kotlin:kotlin-reflect:${libs.versions.kotlin.get()}"

        const val cor = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
        const val cor_jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$COROUTINES_VERSION"
        const val cor_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    }

    object Http {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofit_gson = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val okhttp_log = "com.squareup.okhttp3:logging-interceptor:4.11.0"
    }

    object TcpUdp {
        const val websocket = "org.java-websocket:Java-WebSocket:1.5.3"
    }

    object Log {
        const val log4j = "log4j:log4j:1.2.17"
        const val slf4j = "org.slf4j:slf4j-api:2.0.7"
        const val mindpipe = "de.mindpipe.android:android-logging-log4j:1.0.3"
    }

}