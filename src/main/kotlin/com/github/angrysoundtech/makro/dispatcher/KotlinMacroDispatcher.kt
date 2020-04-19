package com.github.angrysoundtech.makro.dispatcher

import com.github.angrysoundtech.makro.Makro
import com.github.angrysoundtech.makro.MakroConfig
import com.github.angrysoundtech.makro.api.Macro
import com.github.angrysoundtech.makro.scriptengine.kotlin.KtsObjectLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.apache.logging.log4j.Logger
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

/**
 * This dispatcher is a WORK IN PROGRESS and will not work for now.
 *
 * It's not wired up to be able to be used yet.
 */
class KotlinMacroDispatcher(private val logger: Logger, private val macroFolder: File) : MacroDispatcher {

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

    override fun fireMacro(path: String) {
        Makro.logger.debug("Firing Macro: $path")
        if (macros.containsKey(path)) {
            val job = GlobalScope.launch {

                if (MakroConfig.CLIENT.devMode.get()) {
                    compileMacro(path).join()
                }

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