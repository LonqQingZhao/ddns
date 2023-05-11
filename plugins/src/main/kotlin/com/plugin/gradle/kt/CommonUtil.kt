package com.plugin.gradle.kt

import org.gradle.api.Project
import java.text.SimpleDateFormat
import java.util.*

private val Project.tAG: String
    get() = "${name}___"

//fun info(tAG: String, msg: String) {
//    println("${getDate()}    $tAG    $msg")
//}

fun Project.info(msg: String) {
    println("${getDate()}    $tAG    $msg")
}

fun getDate(): String =
    SimpleDateFormat("yyyy/MM/dd.HH:mm:ss.SSS", Locale.CHINESE).format(Date())

val currentOs = Os.LINUX