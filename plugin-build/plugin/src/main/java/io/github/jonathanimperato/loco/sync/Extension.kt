package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.LocoConfig
import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject

@Suppress("UnnecessaryAbstractClass")
abstract class Extension @Inject constructor(project: Project) {
    var resDir: Property<String> = project.objects.property(String::class.java)
    var configs: Property<Array<LocoConfig>> = project.objects.property(Array<LocoConfig>::class.java)
}
