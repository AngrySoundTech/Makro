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
package com.github.angrysoundtech.makro.listener

import com.github.angrysoundtech.makro.Makro
import net.minecraft.client.Minecraft
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.client.event.InputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.lwjgl.glfw.GLFW

/**
 * Temporary way to bind keys as it was very fast to set up.
 */
@Mod.EventBusSubscriber(modid = Makro.ID)
object BindKeyListener {

    var binding = false
    var path: String? = null

    fun beginBinding(path: String) {
        binding = true
        this.path = path

        Minecraft.getInstance().ingameGUI.chatGUI.printChatMessage(StringTextComponent("Press a key to bind to"))
    }

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        if (event.action == GLFW.GLFW_RELEASE) {
            if (binding && path != null) {
                Makro.keybindManager.addKeyBinding(event.key, path!!)

                Minecraft.getInstance().ingameGUI.chatGUI.printChatMessage(StringTextComponent("$path bound to ${event.key}"))

                binding = false
                path = null
            }
        }
    }
}