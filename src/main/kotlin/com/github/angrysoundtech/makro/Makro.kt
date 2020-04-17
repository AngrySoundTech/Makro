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

import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File


@Mod(Makro.ID)
@Suppress("MemberVisibilityCanBePrivate")
object Makro {
    const val ID = "makro"

    val logger: Logger = LogManager.getLogger(ID)

    var keybindManager: KeybindManager
    var macroDispatcher: MacroDispatcher

    var macroFolder: File
    var configFolder: File

    init {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MakroConfig.CLIENT_SPEC)

        macroFolder = File(MakroConfig.CLIENT.folder.get())
        configFolder = File(macroFolder, ".makro")

        // TODO
        //ClientCommandHandler.instance.registerCommand(BindKeyCommand())

        keybindManager = KeybindManager(logger, File(configFolder, "keybinds.json"))
        macroDispatcher = MacroDispatcher(logger, macroFolder)
    }
}
