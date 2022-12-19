package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.Task
import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject

@Suppress("UnnecessaryAbstractClass")
abstract class Extension @Inject constructor(project: Project) {
    var tasks: Property<Array<Task>> = project.objects.property(Array<Task>::class.java)
}