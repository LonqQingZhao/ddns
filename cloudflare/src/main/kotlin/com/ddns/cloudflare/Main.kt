package com.ddns.cloudflare

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object Main {


    lateinit var coroutineContext: CoroutineContext

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            Main.coroutineContext = coroutineContext
            Manager()
            while (isActive) {
                delay(500000)
            }
        }
    }

}