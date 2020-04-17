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

import com.github.angrysoundtech.makro.api.Macro
import com.github.angrysoundtech.makro.scriptengine.KtsObjectLoader
import kotlinx.coroutines.*
import org.apache.logging.log4j.Logger
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

class MacroDispatcher(private val logger: Logger, private val macroFolder: File) {

    private val jobs = mutableListOf<Job>()

    private val macros = mutableMapOf<String, Macro>()

    init {
        setIdeaIoUseFallback()
        loadMacros()
    }

    private fun loadMacros() {
        macroFolder.walk()
                .filter { it.extension == "kts" }
                .map { it.path }
                .forEach { compileMacro(it) }
    }

    private fun compileMacro(path: String) = GlobalScope.launch(Dispatchers.IO) {
        logger.info("Compiling Macro: $path")

        val time = measureTimeMillis {
            Files.newBufferedReader(Paths.get(path)).use {
                macros[path] = KtsObjectLoader().load(it)
            }
        }

        logger.info("Macro compiled in ${time}ms: $path")
    }

    fun fireMacro(path: String) {
        if (macros.containsKey(path)) {
            val job = GlobalScope.launch {

                if (MakroConfig.CLIENT.devMode.get()) {
                    compileMacro(path).join()
                }

                logger.info("Firing macro: $path")
                macros[path]!!.run()
            }

            jobs.add(job)
        } else {
            logger.error("Macro $path is not loaded!")
        }
    }

    fun cancelAll() {
        jobs.forEach { it.cancel() }
    }
}
