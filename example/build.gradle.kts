import io.github.jonathanimperato.loco.sync.models.LocoConfig

plugins {
    java
    id("io.github.jonathanimperato.loco.sync")
}

LocoSync {
    configs.set(arrayOf(
        LocoConfig().also {
            it.apiKey = "ORBkmr5bTsq1D7xJTVvnCD8kphcGfP6W"
            it.fileName = "strings"
            it.languages =  arrayOf("en", "it")
        },
        LocoConfig().also {
            it.apiKey = "X1cphnvDBWMFUmhZdMrmhjZZBQ06E550"
            it.fileName = "strings_notification"
            it.languages = arrayOf("en", "it")
        }
    ))
    resDir.set("$projectDir/src/main/res")
}

