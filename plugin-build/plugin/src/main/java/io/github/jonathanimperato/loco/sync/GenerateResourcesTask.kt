package io.github.jonathanimperato.loco.sync

import io.github.jonathanimperato.loco.sync.models.LocoConfig
import org.gradle.api.DefaultTask
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

abstract class GenerateResourcesTask : DefaultTask() {

    init {
        description = "Generate loco strings file task"
        group = BasePlugin.BUILD_GROUP
    }

    @get:Input
    abstract val configs: Property<Array<LocoConfig>>

    @TaskAction
    @Suppress("NestedBlockDepth")
    fun generate() {
        configs.get().forEach { config ->
            val resDir = config.resDir
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
                    val textContent = inputStream.bufferedReader().readText().apply {
                        if (!config.isAndroid) unescapeXml()
                    }
                    file.writeText(textContent, Charsets.UTF_8)
                }
            }
        }
    }
    /**
     * Estensione per rimuovere gli escape XML.
     */
    fun String.unescapeXml(): String {
        return this.replace("\\\\", "\\") // Rimuove i doppi backslash
            .replace("\\'", "'")         // Rimuove l'escape dal carattere singolo
            .replace("\\n", "\n")        // Sostituisce le sequenze di newline con newline reali
            .replace("\\t", "\t")        // Sostituisce le tabulazioni
            .replace("&lt;", "<")        // Decodifica i caratteri HTML
            .replace("&gt;", ">")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#39;", "'")
    }
}
