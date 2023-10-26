package com.ddns.cloudflare

import com.ddns.cloudflare.api.*
import com.ddns.cloudflare.data.Response
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext


class Manager(override val coroutineContext: CoroutineContext) : CoroutineScope {

    private val waitClock = AtomicLong(1000 * 5)

    init {
        launch {
            while (isActive) {
                try {
                    info("start")
                    val data = withTimeout(20000) {
                        info("start query dns")
                        ApiManager.api.queryDns(zoneId)
                    }
                    info("getData get data:${data.result.size}")
                    withTimeout(20000) {
                        check(data)
                    }
                    info("job await")
                    delay(waitClock.get())
                    info("job await over")
                } catch (e: Exception) {
                    fail("error:${currentCoroutineContext()}", e)
                    waitClock.updateAndGet { (1000 * 60 * 60 * 4).toLong() } // 4小时
                    delay(waitClock.get())
                }
                info("job all over")
            }
        }
    }

    private suspend fun check(data: Response<List<Response.Result>>) {
        if (data.success) {
            val result = data.result.find { it.name == changeDns }
            val ipResult = ApiManager.api.queryIP()
            info("api data:${ipResult.data}")
            if (!ipResult.data.isNullOrEmpty() && ipResult.data != result?.content && result != null) {
                info("change ip:${ipResult.data}")
                val r = ApiManager.api.updateIP(zoneId, result.id!!, result.copy(content = ipResult.data))
                if (r.success) {
                    info("change success")
                    waitClock.getAndSet(1000 * 60 * 60 * 5) // 5小时
                } else {
                    info("change error")
                    waitClock.getAndSet(1000 * 60 * 5) // 5分钟
                }
            } else {
                info("needn't change")
                waitClock.getAndSet(1000 * 60 * 60) // 1分钟
            }
        }

    }

}