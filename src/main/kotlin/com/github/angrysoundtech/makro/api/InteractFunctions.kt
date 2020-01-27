package com.github.angrysoundtech.makro.api

import com.github.angrysoundtech.makro.util.KeyBindUtil
import net.minecraft.client.Minecraft

object InteractFunctions {

    private val mc = Minecraft.getMinecraft()

    fun attack(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindAttack, time)
        Thread.sleep(time)
    }

    fun use(time: Long = 100) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindUseItem, time)
    }

    fun eat() {
        use(3000)
    }
}