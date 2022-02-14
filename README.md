# loco-strings-sync

A custom Gradle Plugin useful to sync loco string resources to local projects.

## Usage

- Import the plugin in your module-level build.gradle
  ```kotlin
  plugins {
    id("TODO")
  }
  ```
- Add:
  ```LocoSync {
    LocoSync.LocoConfig[] array = [
            new LocoSync.LocoConfig(null).with {
                it.apiKey = "LOCO_KEY_1"
                it.fileName = "strings"
                it.languages = ["en", "it"]
                it
            },
            new LocoSync.LocoConfig(null).with {
                it.apiKey = "LOCO_KEY_2"
                it.fileName = "strings_second"
                it.languages = ["en", "it"]
                it
            }
    ]
    configs = array
    resDir = "$projectDir/src/main/res"
  }
  ```
- Finally, after syncing, you will have the task `LocoStringsSync` that will automatically sync all you chosen strings.
