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
package com.github.angrysoundtech.makro.command
//
//import com.github.angrysoundtech.makro.Makro
//import net.minecraft.command.CommandBase
//import net.minecraft.command.ICommandSender
//import net.minecraft.server.MinecraftServer
//import net.minecraft.util.math.BlockPos
//import net.minecraft.util.text.TextComponentString
//import net.minecraft.util.text.TextFormatting
//import org.lwjgl.input.Keyboard
//
//class BindKeyCommand : CommandBase() {
//
//    override fun getName() = "bind"
//
//    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
//        if (args.size != 2) {
//            sender.sendMessage(TextComponentString(TextFormatting.RED.toString() + getUsage(sender)))
//            return
//        }
//
//        val upcased = args[1].toUpperCase()
//
//        val macro = args[0]
//        val key = Keyboard.getKeyIndex(upcased)
//
//        if (key == 0) {
//            sender.sendMessage(TextComponentString(TextFormatting.RED.toString() + "Unknown key: $upcased"))
//            return
//        }
//
//        Makro.keybindManager.addKeyBinding(key, macro)
//
//        sender.sendMessage(TextComponentString(TextFormatting.GREEN.toString() + "Bound $macro to key $key ($upcased)"))
//    }
//
//    override fun getUsage(sender: ICommandSender): String {
//        return "Usage: /bind <file> <key>"
//    }
//
//    override fun getTabCompletions(server: MinecraftServer, sender: ICommandSender, args: Array<String>, targetPos: BlockPos?): MutableList<String> {
//        if (args.size == 1) {
//            val macros = Makro.macroFolder
//                    .walk()
//                    .filter { it.extension == "kts" }
//                    .map { it.relativeTo(Makro.macroFolder) }
//                    .toList()
//
//            return getListOfStringsMatchingLastWord(args, macros)
//        }
//
//        return mutableListOf()
//    }
//}
