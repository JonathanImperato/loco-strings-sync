package io.github.jonathanimperato.loco.sync.models

import io.github.jonathanimperato.loco.sync.models.LocoConfig

open class Task {
    var taskName: String = "LocoSync"
    var resDir: String = ""
    var configs: Array<LocoConfig> = emptyArray()
}