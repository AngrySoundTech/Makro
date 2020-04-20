package com.github.angrysoundtech.makro.gui.screen

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.button.Button
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.fml.client.config.GuiButtonExt

// TODO Move to another file
const val BUTTON_WIDTH = 200
const val BUTTON_HEIGHT = 30
const val SEPARATION = 4

/**
 * Temporary GUI for testing and running commands. Should be enough for a user too for now...
 */
class DebugScreen : Screen(StringTextComponent("Makro Functions")) {

    override fun init() {
        this.buttons.clear()

        val xPos = width / 2 - BUTTON_WIDTH / 2
        val yPos = height / 4 + 8 - (BUTTON_HEIGHT + SEPARATION)

        addButton(GuiButtonExt(
                xPos, yPos,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                "Bind Macro",
                BindMacroButton()
        ))
    }

    class BindMacroButton : Button.IPressable {

        override fun onPress(button: Button) {
            Minecraft.getInstance().displayGuiScreen(MacroSelectScreen())
        }

    }
}