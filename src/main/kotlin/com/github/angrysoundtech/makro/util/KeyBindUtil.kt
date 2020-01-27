package com.github.angrysoundtech.makro.util

import kotlinx.coroutines.delay
import net.minecraft.client.settings.KeyBinding
import java.util.*
import kotlin.concurrent.schedule

object KeyBindUtil {

    fun holdKeybind(kb: KeyBinding, time: Long) {
        holdKeybind(kb.keyCode, time)
    }

    fun holdKeybind(keycode: Int, time: Long) {
        if (time == 0L) {
            KeyBinding.setKeyBindState(keycode, false)
            return
        }

        KeyBinding.setKeyBindState(keycode, true)

        if (time < 0) {
            return
        }

        Timer().schedule(time) {
            KeyBinding.setKeyBindState(keycode, false)
        }
    }
}