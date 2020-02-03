package com.github.angrysoundtech.makro

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

class KeybindManager {

    private val file = File(ModConfig.folder + File.separator + ".makro" + File.separator + "keybinds.json")
    private val gson = GsonBuilder().setPrettyPrinting().create()

    var keybinds = mutableMapOf<Int, String>()

    init {
        if (!file.exists()) {
            save()
        }

        load()
    }

    fun load() {
        // We need this stupid typetoken to tell GSON what's up
        val mapType = object: TypeToken<Map<Int, String>>() {}.type

        // We're loading, destroy whatever was there and put the new info in.
        keybinds = gson.fromJson(file.readText(), mapType)
    }

    fun save() {
        // Create the parent folder if it does not exist
        file.parentFile.mkdirs()

        // Create the file if it does not exist
        file.createNewFile()

        file.writeText(gson.toJson(keybinds))
    }

    fun addKeyBinding(key: Int, script: String) {
        keybinds[key] = script
        save()
    }

    fun removeKeyBinding(key: Int) {
        keybinds.remove(key)
        save()
    }
}
