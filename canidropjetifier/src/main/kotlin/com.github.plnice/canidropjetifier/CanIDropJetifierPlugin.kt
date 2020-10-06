package com.github.plnice.canidropjetifier

import org.gradle.api.Action
import org.gradle.api.Project

import org.gradle.kotlin.dsl.*

open class CanIDropJetifierPluginExtension {
    var verbose: Boolean = false
    var includeModules: Boolean = true
    var analyzeOnlyAndroidModules: Boolean = true
    var configurationRegex: String = ".*RuntimeClasspath"
    var parallelMode: Boolean = false
    var parallelModePoolSize: Int? = null
    var assertive: Boolean = false
}

class CanIDropJetifierPlugin : AllOpenPlugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        val extension = extensions.create<CanIDropJetifierPluginExtension>("canIDropJetifier")
        tasks {
            register("canIDropJetifier", CanIDropJetifierTask::class, Action {
                verbose = extension.verbose
                includeModules = extension.includeModules
                analyzeOnlyAndroidModules = extension.analyzeOnlyAndroidModules
                configurationRegex = extension.configurationRegex
                parallelMode = extension.parallelMode
                parallelModePoolSize = extension.parallelModePoolSize
                enforcer = if (extension.assertive) StrictAssert() else IgnoreAll()
            })
        }
    }
}
