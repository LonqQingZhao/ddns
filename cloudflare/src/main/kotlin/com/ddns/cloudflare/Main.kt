package com.ddns.cloudflare

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

object Main {


    private val coroutineContext: CoroutineContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking(coroutineContext) {
            Manager(coroutineContext)
            while (isActive) {
                delay(1000)
            }
        }
    }

}