package com.ddns.cloudflare

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            Manager()
            while (isActive) {
                delay(500000)
            }
        }
    }

}