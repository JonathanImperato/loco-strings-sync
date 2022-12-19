package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.LocoConfig

abstract class Task {
    var taskName: String = "LocoSync"
    var resDir: String = ""
    var configs: Array<LocoConfig> = emptyArray()
}