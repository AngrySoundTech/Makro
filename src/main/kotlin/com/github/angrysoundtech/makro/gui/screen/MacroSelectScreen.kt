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
import com.github.angrysoundtech.makro.listener.BindKeyListener
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.client.config.GuiButtonExt

/**
 * Temporary macro binding. god I want to die when I do guis
 */
class MacroSelectScreen : Screen(StringTextComponent("Select Macro to Bind")) {

    override fun init() {
        this.buttons.clear()

        val xPos = width / 2 - BUTTON_WIDTH / 2
        var yPos = height / 4 + 8 - (BUTTON_HEIGHT / 2 + SEPARATION)

        Makro.macroFolder
                .walk()
                .filter { it.extension in Makro.supportedExtensions }
                .map { it.relativeTo(Makro.macroFolder).path }
                .forEach {
                    addButton(GuiButtonExt(
                            xPos, yPos,
                            BUTTON_WIDTH,
                            BUTTON_HEIGHT / 2,
                            it,
                            ChooseMacroButton(it)
                    ))

                    yPos += BUTTON_HEIGHT / 2 + SEPARATION
                }

    }

    class ChooseMacroButton(val script: String) : Button.IPressable {

        override fun onPress(button: Button) {
            BindKeyListener.beginBinding(script)
            Minecraft.getInstance().displayGuiScreen(null);
        }
    }

}
