package io.github.jonathanimperato.loco.sync

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "LocoSync"

abstract class LocoSyncPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, Extension::class.java, project)
        val taskName = project.extensions.taskName
        project.tasks.register(taskName, GenerateResourcesTask::class.java) {
            it.resDir.set(extension.resDir)
            it.configs.set(extension.configs)
        }
    }
}
