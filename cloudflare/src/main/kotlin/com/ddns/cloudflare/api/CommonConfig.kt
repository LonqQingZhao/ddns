package com.ddns.cloudflare.api

import org.apache.logging.log4j.LogManager

const val email = "zhaolongqing1@gmail.com"
const val authKey = "31950d6824002bc39b993c3d3b551aff43f0d"
const val auth = "INcySuoUTiho5Q6oUe6S31YK-SWYPGHVsmtozaoX"
const val zoneId = "317274a38b4ce5ec01e3d4ab2a14e065"

//const val accountId = "dcbcdb5062560841bbae35a53b3a9ea1"
const val changeDns = "www.home.zhaogege.top"
//const val changeDns = "www.test.home.zhaogege.top"

val logger = LogManager.getLogger(ApiManager::class.java)!!

inline fun <reified T> T.getTag(): String =
    T::class.simpleName ?: ""

inline fun <reified T> T.info(msg: String) {
    logger.info(" ${getTag()}    $msg")
}

fun fail(msg: String, exception: Throwable?) {
    logger.error(msg, exception)
}