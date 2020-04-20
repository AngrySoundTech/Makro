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
