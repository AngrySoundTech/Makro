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
