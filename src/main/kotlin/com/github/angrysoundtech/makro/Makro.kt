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

import com.github.angrysoundtech.makro.command.BindKeyCommand
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

@Mod(
        modid = Makro.ID,
        version = Makro.VERSION,
        name = "Makro",
        clientSideOnly = true,
        canBeDeactivated = true,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
        dependencies = "required-after:forgelin;"
)
@Suppress("MemberVisibilityCanBePrivate")
@Mod.EventBusSubscriber(modid = Makro.ID)
object Makro {

    const val ID = "makro"
    const val VERSION = "@VERSION@"

    val logger: Logger = LogManager.getLogger(ID)

    lateinit var keybindManager: KeybindManager
    lateinit var macroDispatcher: MacroDispatcher

    lateinit var macroFolder: File
    lateinit var configFolder: File

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        macroFolder = File(ModConfig.folder)
        configFolder = File(macroFolder, ".makro")

        ClientCommandHandler.instance.registerCommand(BindKeyCommand())
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        keybindManager = KeybindManager(logger, File(configFolder, "keybinds.json"))
        macroDispatcher = MacroDispatcher(logger, macroFolder)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {

    }
}
