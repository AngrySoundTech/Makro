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
package com.github.angrysoundtech.makro.gui.screen

import com.github.angrysoundtech.makro.Makro
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.client.config.GuiButtonExt

class MacroUnbindScreen : Screen(StringTextComponent("Select a Macro to unbind")) {

    override fun init() {
        this.buttons.clear()

        val xPos = width / 2 - BUTTON_WIDTH / 2
        var yPos = height / 4 + 8 - (BUTTON_HEIGHT / 2 + SEPARATION)

        Makro.keybindManager.keybinds.forEach { k, v ->
            addButton(GuiButtonExt(
                    xPos, yPos,
                    BUTTON_WIDTH,
                    BUTTON_HEIGHT / 2,
                    "$k to $v",
                    UnbindMacroButton(k)
            ))

            yPos += BUTTON_HEIGHT / 2 + SEPARATION
        }
    }

    class UnbindMacroButton(val key: Int) : Button.IPressable {

        override fun onPress(button: Button) {
            Makro.keybindManager.removeKeyBinding(key)
            Minecraft.getInstance().displayGuiScreen(MacroUnbindScreen()) // hack gui refresh
        }
    }
}
