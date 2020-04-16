/**
 * The MIT License
 *
 * Copyright (c) 2019 SoundTech (angrysoundtech@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
