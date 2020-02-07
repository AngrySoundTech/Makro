package com.github.angrysoundtech.makro.listener

import com.github.angrysoundtech.makro.Makro
import com.github.angrysoundtech.makro.ModConfig
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import org.lwjgl.input.Keyboard
import java.io.File


@Mod.EventBusSubscriber(modid = Makro.ID)
object MacroFireListener {

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        val key = Keyboard.getEventKey()

        Makro.keybindManager.keybinds[key]?.let {
            if (Keyboard.isKeyDown(key)) {
                Makro.macroDispatcher.fireMacro(ModConfig.folder + File.separator + it)
            }
        }
    }
}
