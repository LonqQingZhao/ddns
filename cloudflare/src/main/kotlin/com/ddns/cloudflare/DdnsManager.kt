package com.ddns.cloudflare

import com.ddns.cloudflare.data.Response
import kotlinx.coroutines.*
import com.ddns.cloudflare.api.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext


class DdnsManager(override val coroutineContext: CoroutineContext) : CoroutineScope {

    private val waitClock = AtomicLong(1000 * 5)

    init {
        launch {
            while (isActive) {
                try {
                    info("开始进程")

                    info("任务等待")
                    delay(waitClock.get())
                    info("任务等待结束")

                    val data = withTimeout(20000) {
                        info("开始查询ddns")
                        ApiManager.api.queryDns(zoneId)
                    }
                    info("获取到ddns数据:${data.result.size}")
                    withTimeout(20000) {
                        check(data)
                    }

                } catch (e: Exception) {
                    fail("ddns异常:${currentCoroutineContext()}", e)
                    waitClock.updateAndGet { (1000 * 60 * 5).toLong() } // 5分钟
                    delay(waitClock.get())
                }
                info("任务执行完成一轮")
            }
        }
    }

    private suspend fun check(data: Response<List<Response.Result>>) {
        if (data.success) {
            val result = data.result.find { it.name == changeDns }
            val ipResult = ApiManager.api.queryIP()
            info("查询到当前ip:${ipResult.data}")
            if (!ipResult.data.isNullOrEmpty() && ipResult.data != result?.content && result != null) {
                info("修改域名:${ipResult.data}")
                val r = ApiManager.api.updateIP(zoneId, result.id!!, result.copy(content = ipResult.data))
                if (r.success) {
                    info("修改成功")
                    waitClock.getAndSet(1000 * 60 * 60 * 5) // 5小时
                } else {
                    info("修改失败")
                    waitClock.getAndSet(1000 * 60 * 5) // 5分钟
                }
            } else {
                info("不需要修改")
                waitClock.getAndSet(1000 * 60 * 60) // 1小时
            }
        }

    }

}