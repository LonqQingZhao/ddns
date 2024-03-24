package com.ddns.cloudflare

import com.ddns.cloudflare.api.fail
import com.ddns.cloudflare.api.info
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.coroutines.CoroutineContext


class CheckNetWorkManager(override val coroutineContext: CoroutineContext) : CoroutineScope {

    companion object {
        private const val MINUTE = 60L
        private const val CHECK_TIME = 5L * MINUTE
        private const val CHECK_COUNT = 3L
        private const val REBOOT_DELAY_TIME = 5L * MINUTE
    }

    private val netWorkCheck = MutableStateFlow<Throwable?>(null)

    private var count = 0

    init {
        launch {
            while (isActive) {
                try {
                    val reachable = execFun("ping", "-c", "1", "www.baidu.com")
                    if (!reachable) {
                        netWorkCheck.update { RuntimeException("unreachable") }
                    } else {
                        netWorkCheck.update { null }
                    }
                } catch (e: Exception) {
                    netWorkCheck.update { RuntimeException("ping 出问题了", e) }

                } finally {
                    //每5分钟检测一次
                    delay(CHECK_TIME * 1000)
                }
            }
        }
        launch {
            var job: Job? = null
            netWorkCheck.collectLatest {
                if (it != null) {
                    count += 1
                    fail("当前网络不可用！！", it)
                } else {
                    if (count != 0) {
                        info("网络恢复！！")
                        job?.cancel()
                    }
                    count = 0
                }
                if (count >= CHECK_COUNT) {
                    if (job != null) {
                        return@collectLatest
                    }
                    info("进入重启状态！10分钟后重启")
                    job = launch {
                        delay(REBOOT_DELAY_TIME * 1000)
                        try {
                            val result = execFun("sudo", "reboot")
                            if (result) {
                                info("重启成功")
                            } else {
                                info("重启失败")
                            }
                        } catch (e: Exception) {
                            fail("重启失败!!", e)
                        }
                    }
                    job?.invokeOnCompletion {
                        info("执行完毕")
                        job = null
                    }
                }
            }
        }
    }

    private fun execFun(vararg cmd: String): Boolean {
        val processBuilder = ProcessBuilder(cmd.toList())
        val process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String? = reader.readLine()
        while (line != null) {
            line = reader.readLine()
            line?.apply {
                info(this)
            }
        }
        val exitCode = process.waitFor()
        return exitCode == 0
    }

}