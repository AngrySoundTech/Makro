package com.github.angrysoundtech.makro.listener

import com.github.angrysoundtech.makro.Makro
import com.github.angrysoundtech.makro.ModConfig
import com.github.angrysoundtech.makro.api.Macro
import com.github.angrysoundtech.makro.scriptengine.KtsObjectLoader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import org.lwjgl.input.Keyboard
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

@Mod.EventBusSubscriber(modid = Makro.ID)
object MacroFireListener {

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        val key = Keyboard.getEventKey()

        Makro.keybindManager.keybinds[key]?.let {
            val scriptReader = Files.newBufferedReader(Paths.get(ModConfig.folder + File.separator + it))

            setIdeaIoUseFallback()

            GlobalScope.launch {
                println(measureTimeMillis {
                    val loadedObj: Macro = KtsObjectLoader().load(scriptReader)

                    loadedObj.run()
                })
            }
        }
    }
}
