package com.github.angrysoundtech.makro.api.facade

import com.github.angrysoundtech.makro.util.KeyBindUtil
import net.minecraft.client.Minecraft

object InteractFacade {

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
