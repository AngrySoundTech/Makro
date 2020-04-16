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

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.io.File

@Config(modid = Makro.ID)
@Config.LangKey("makro.config.title")
object ModConfig {

    @JvmField
    @Config.Comment("Macro root folder")
    @Config.LangKey("makro.config.folder")
    @Config.RequiresMcRestart
    var folder = Minecraft.getMinecraft().mcDataDir.canonicalPath + File.separator + "macros"

    @JvmField
    @Config.Comment("Enable Development Mode. Macros recompile every run.")
    @Config.LangKey("Makro.config.devmode")
    var devMode = false

    fun update() {
        ConfigManager.sync(Makro.ID, Config.Type.INSTANCE)
    }

    @Mod.EventBusSubscriber(modid = Makro.ID)
    private object EventHandler {

        @SubscribeEvent
        fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
            if (event.modID == Makro.ID) {
                ModConfig.update()
            }
        }
    }
}
