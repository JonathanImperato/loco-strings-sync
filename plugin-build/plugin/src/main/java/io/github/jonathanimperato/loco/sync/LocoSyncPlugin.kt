package io.github.jonathanimperato.loco.sync

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "LocoSync"
abstract class LocoSyncPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, Extension::class.java, project)
        extension.tasks.getOrElse(emptyArray()).forEach { task ->
            project.tasks.register(task.taskName, GenerateResourcesTask::class.java) {
                it.resDir.set(task.resDir)
                it.configs.set(task.configs)
            }
        }
    }
}
