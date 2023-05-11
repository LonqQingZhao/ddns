package com.version

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withGroovyBuilder

object VersionConfigPlugin : Plugin<Project> {

    init {
        withGroovyBuilder {
            println("start ${this@VersionConfigPlugin}")
        }
    }

    override fun apply(target: Project) {
        
    }
}