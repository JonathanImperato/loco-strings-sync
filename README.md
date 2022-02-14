# loco-strings-sync

A custom Gradle Plugin useful to sync loco string resources to local projects.

## Usage

- Import the plugin in your module-level build.gradle
  ```kotlin
  plugins {
    id("io.github.jonathanimperato.loco.sync")
  }
  ```
- Add:
  ```LocoSync {
        configs.set(arrayOf(
            LocoConfig().also {
                it.apiKey = "API_KEY1"
                it.fileName = "strings"
                it.languages =  arrayOf("en", "it")
            },
            LocoConfig().also {
                it.apiKey = "API_KEY2"
                it.fileName = "strings_second"
                it.languages = arrayOf("en", "it")
            }
        ))
        resDir.set("$projectDir/src/main/res")
      }```
  
- Finally, after syncing, you will have the task `LocoStringsSync` that will automatically sync all you chosen strings.
