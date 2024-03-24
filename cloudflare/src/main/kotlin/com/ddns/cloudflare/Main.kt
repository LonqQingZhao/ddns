package com.ddns.cloudflare

import com.ddns.cloudflare.api.fail
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext


object Main {


    private val coroutineContext: CoroutineContext = Executors
        .newSingleThreadExecutor()
        .asCoroutineDispatcher() + CoroutineExceptionHandler { coroutineContext, throwable ->
        fail("error:${coroutineContext}", throwable)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking(coroutineContext) {
            // 网络检查
            CheckNetWorkManager(coroutineContext)
            // ddns检查
            DdnsManager(coroutineContext)
            while (isActive) {
                delay(1000)
            }
        }
    }

}