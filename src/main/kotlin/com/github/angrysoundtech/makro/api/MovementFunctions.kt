package com.github.angrysoundtech.makro.api

import com.github.angrysoundtech.makro.util.KeyBindUtil
import kotlinx.coroutines.delay
import net.minecraft.client.Minecraft

object MovementFunctions {

    private val mc = Minecraft.getMinecraft()

    fun forward(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindForward, time)
        Thread.sleep(time)
    }

    fun back(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindBack, time)
        Thread.sleep(time)
    }

    fun left(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindLeft, time)
        Thread.sleep(time)
    }

    fun right(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindRight, time)
        Thread.sleep(time)
    }

    fun jump() {
        if (mc.player.onGround) {
            mc.player.jump()
        }
    }

    fun sneak(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindSneak, time)
    }

    fun sprint(bool: Boolean) {
        mc.player.isSprinting = bool
    }
}
