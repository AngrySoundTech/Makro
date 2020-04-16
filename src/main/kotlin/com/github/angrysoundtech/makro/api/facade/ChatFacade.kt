package com.github.angrysoundtech.makro.api.facade

import net.minecraft.client.Minecraft
import net.minecraft.util.text.TextComponentString

/**
 * All things dealing with chat functionality.
 */
object ChatFacade {

    init {
        println("CRATED")
    }

    /**
     * Log a raw string to the chat.
     * Only visible to the player.
     */
    fun log(text: String) {
        Minecraft.getMinecraft().ingameGUI.chatGUI.printChatMessage(TextComponentString(text))
    }

    fun c(text: String) {
        println(text)
    }
}
