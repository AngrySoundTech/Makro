/**
 * The MIT License
 *
 * Copyright (c) 2019 SoundTech (angrysoundtech@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.angrysoundtech.makro.api.facade

import com.github.angrysoundtech.makro.util.KeyBindUtil
import net.minecraft.client.Minecraft

object MovementFacade {

    private val mc = Minecraft.getInstance()

    @JvmStatic
    fun forward(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindForward, time)
        Thread.sleep(time)
    }

    @JvmStatic
    fun back(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindBack, time)
        Thread.sleep(time)
    }

    @JvmStatic
    fun left(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindLeft, time)
        Thread.sleep(time)
    }

    @JvmStatic
    fun right(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindRight, time)
        Thread.sleep(time)
    }

    @JvmStatic
    fun jump() {
        if (mc.player.onGround) {
            mc.player.jump()
        }
    }

    @JvmStatic
    fun sneak(time: Long) {
        KeyBindUtil.holdKeybind(mc.gameSettings.keyBindSneak, time)
    }

    @JvmStatic
    fun sprint(bool: Boolean) {
        mc.player.isSprinting = bool
    }
}
