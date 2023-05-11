package com.ddns.cloudflare

import com.ddns.cloudflare.api.*
import com.ddns.cloudflare.data.Response
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class Manager : CoroutineScope {

    init {
        launch {
            while (isActive) {
                val data = ApiManager.api.queryDns(zoneId)
                info("getData:$data")
                check(data)
                delay(1000 * 60 * 10)
            }
        }
    }

    private suspend fun check(data: Response<List<Response.Result>>) {
        if (data.success) {
            val result = data.result.find { it.name == changeDns }
            val ipResult = ApiManager.api.queryIP()
            info("api data:$ipResult")
            if (!ipResult.ip.isNullOrEmpty() && ipResult.ip != result?.content && result != null) {
                info("change ip:${ipResult.ip}")
                val r = ApiManager.api.updateIP(zoneId, result.id!!, result.copy(content = ipResult.ip))
                if (r.success) {
                    info("change success")
                } else {
                    info("change error")
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            fail("error")
            fail(throwable)
        }
}