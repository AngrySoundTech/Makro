package com.github.angrysoundtech.makro.command

import com.github.angrysoundtech.makro.Makro
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextFormatting
import org.lwjgl.input.Keyboard

class BindKeyCommand : CommandBase() {

    override fun getName() = "bind"

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        if (args.size != 2) {
            sender.sendMessage(TextComponentString(TextFormatting.RED.toString() + getUsage(sender)))
            return
        }

        val upcased = args[1].toUpperCase()

        val macro = args[0]
        val key = Keyboard.getKeyIndex(upcased)

        if (key == 0) {
            sender.sendMessage(TextComponentString(TextFormatting.RED.toString() + "Unknown key: $upcased"))
            return
        }

        Makro.keybindManager.addKeyBinding(key, macro)

        sender.sendMessage(TextComponentString(TextFormatting.GREEN.toString() + "Bound $macro to key $key ($upcased)"))
    }

    override fun getUsage(sender: ICommandSender): String {
        return "Usage: /bind <file> <key>"
    }

    override fun getTabCompletions(server: MinecraftServer, sender: ICommandSender, args: Array<String>, targetPos: BlockPos?): MutableList<String> {
        if (args.size == 1) {
            val macros = Makro.macroFolder
                    .walk()
                    .filter { it.extension == "kts" }
                    .map { it.relativeTo(Makro.macroFolder) }
                    .toList()

            return getListOfStringsMatchingLastWord(args, macros)
        }

        return mutableListOf()
    }
}
