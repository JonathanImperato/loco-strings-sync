package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.LocoConfig
import org.gradle.api.DefaultTask
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.impldep.org.glassfish.jaxb.runtime.v2.runtime.Coordinator
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

abstract class GenerateResourcesTask : DefaultTask() {

    init {
        description = "Generate loco strings file task"
        group = BasePlugin.BUILD_GROUP
    }

    @get:Input
    abstract val resDir: Property<String>

    @get:Input
    abstract val configs: Property<Array<LocoConfig>>

    @TaskAction
    @Suppress("NestedBlockDepth")
    fun generate() {
        val resDir = resDir.get()
        configs.get().forEach { config ->
            val defLang = config.defaultLanguage
            config.languages.forEach {
                var lang = it
                val url = URL("https://localise.biz/api/export/locale/$lang.xml")
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.run {
                    addRequestProperty("Authorization", "Loco ${config.apiKey}")
                    addRequestProperty("Accept-Charset", "utf-8")
                    doOutput = true
                    requestMethod = "GET"
                    val appendix = when {
                        lang != defLang -> {
                            if (lang.contains("-")) lang = lang.replace("-", "-r")
                            "-$lang"
                        }
                        else -> ""
                    }
                    val directory = File("$resDir/values$appendix/")
                    if (!directory.exists()) directory.mkdir()
                    val file = File(directory.absolutePath + "/" + config.fileName + ".xml")
                    file.writeText((content as String), Charsets.UTF_8)
                }
            }
        }
    }
}
