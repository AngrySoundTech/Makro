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

                if (ModConfig.devMode) {
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
        jobs.forEach(Job::cancel)
    }
}
