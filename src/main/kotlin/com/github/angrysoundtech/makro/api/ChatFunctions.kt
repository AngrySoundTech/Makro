package com.github.angrysoundtech.makro.api

import net.minecraft.client.Minecraft
import net.minecraft.util.text.TextComponentString

object ChatFunctions {

    fun log(string: String) {
        Minecraft.getMinecraft().ingameGUI.chatGUI.printChatMessage(TextComponentString(string))
    }
}
