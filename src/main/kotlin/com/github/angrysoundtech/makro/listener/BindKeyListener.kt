package com.github.angrysoundtech.makro.listener

import com.github.angrysoundtech.makro.Makro
import net.minecraft.client.Minecraft
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.lwjgl.glfw.GLFW

/**
 * Temporary way to bind keys as it was very fast to set up.
 */
@Mod.EventBusSubscriber(modid = Makro.ID)
object BindKeyListener {

    var binding = false
    var path: String? = null

    fun beginBinding(path: String) {
        binding = true
        this.path = path

        Minecraft.getInstance().ingameGUI.chatGUI.printChatMessage(StringTextComponent("Press a key to bind to"))
    }

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        if (event.action == GLFW.GLFW_RELEASE) {
            if (binding && path != null) {
                Makro.keybindManager.addKeyBinding(event.key, path!!)

                Minecraft.getInstance().ingameGUI.chatGUI.printChatMessage(StringTextComponent("$path bound to ${event.key}"))

                binding = false
                path = null
            }
        }
    }
}