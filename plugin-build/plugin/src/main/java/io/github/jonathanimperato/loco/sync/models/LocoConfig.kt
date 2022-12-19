package io.github.jonathanimperato.loco.sync.models

open class LocoConfig {
    var apiKey: String = ""
    var fileName: String = ""
    var languages: Array<String> = arrayOf("en")
    var defaultLanguage: String = "en"
    var resDir : String = ""
}
