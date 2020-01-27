package com.github.angrysoundtech.makro.api

import com.github.angrysoundtech.makro.util.KeyBindUtil
import net.minecraft.client.Minecraft

object AttackFunctions {

    private val mc = Minecraft.getMinecraft()

    fun attack(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindAttack, time)
        Thread.sleep(time)
    }
}