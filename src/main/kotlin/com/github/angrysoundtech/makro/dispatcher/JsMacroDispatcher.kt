package com.github.angrysoundtech.makro.dispatcher

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.logging.log4j.Logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngineManager

class JsMacroDispatcher(private val logger: Logger, private val macroFolder: File) : MacroDispatcher {

    override fun fireMacro(path: String) {
        logger.debug("Firing Macro: $path")

        val job = GlobalScope.launch {
            Files.newBufferedReader(Paths.get(path)).use {
                ScriptEngineManager().getEngineByName("nashorn").eval(it)
            }
        }
    }
}
