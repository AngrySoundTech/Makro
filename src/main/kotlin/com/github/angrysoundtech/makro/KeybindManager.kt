package com.github.angrysoundtech.makro

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.apache.logging.log4j.Logger
import java.io.File

class KeybindManager(private val logger: Logger, private val file: File) {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    var keybinds = mutableMapOf<Int, String>()

    init {
        if (!file.exists()) {
            logger.info("Unable to locate keybind config! Creating new one.")
            save()
        }

        load()
    }

    fun load() {
        logger.info("Loading keybind config...")

        // We need this stupid typetoken to tell GSON what's up
        val mapType = object: TypeToken<Map<Int, String>>() {}.type

        // We're loading, destroy whatever was there and put the new info in.
        keybinds = gson.fromJson(file.readText(), mapType)

        logger.info("Keybind config loaded!")
    }

    fun save() {
        logger.info("Saving keybind config...")

        // Create the parent folder if it does not exist
        file.parentFile.mkdirs()

        // Create the file if it does not exist
        file.createNewFile()

        file.writeText(gson.toJson(keybinds))

        logger.info("Keybind config saved!")
    }

    fun addKeyBinding(key: Int, script: String) {
        logger.info("Adding key binding: $key to $script")
        keybinds[key] = script
        save()
    }

    fun removeKeyBinding(key: Int) {
        logger.info("Removing key binding: $key")
        keybinds.remove(key)
        save()
    }
}
