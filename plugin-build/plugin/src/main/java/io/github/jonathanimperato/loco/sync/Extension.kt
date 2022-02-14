package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.LocoConfig
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class Extension @Inject constructor() {
    abstract var resDir: Property<String>
    abstract var configs: Property<Array<LocoConfig>>
}