package com.github.angrysoundtech.makro.listener

import com.github.angrysoundtech.makro.Makro
import com.github.angrysoundtech.makro.gui.screen.DebugScreen
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import org.lwjgl.glfw.GLFW

@Mod.EventBusSubscriber(modid = Makro.ID)
object KeyInputListener {

    var debugKey = KeyBinding("DEBUG KEY", GLFW.GLFW_KEY_V, "Makro")

    init {
        ClientRegistry.registerKeyBinding(debugKey)
    }

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        if (debugKey.isPressed) {
            Minecraft.getInstance().displayGuiScreen(DebugScreen())
        }
    }
}