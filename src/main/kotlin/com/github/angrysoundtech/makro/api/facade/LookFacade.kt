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

import net.minecraft.client.Minecraft

object LookFacade {

    private val mc = Minecraft.getInstance()

    @JvmStatic val SOUTH_YAW = 0F
    @JvmStatic val WEST_YAW = 90F
    @JvmStatic val NORTH_YAW = -180F
    @JvmStatic val EAST_YAW = -90F
    @JvmStatic val STRAIGHT_PITCH = 0F

    @JvmStatic
    fun getPitch(): Float {
        return mc.player.rotationPitch
    }

    @JvmStatic
    fun getYaw(): Float {
        return mc.player.rotationYaw
    }

    @JvmStatic
    fun look(yaw: Float, pitch: Float) {
        mc.player.rotationPitch = pitch
        mc.player.rotationYaw = yaw
    }
}
