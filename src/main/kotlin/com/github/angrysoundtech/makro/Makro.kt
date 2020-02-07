package com.github.angrysoundtech.makro

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.input.Keyboard
import java.io.File

@Mod(
        modid = Makro.ID,
        version = Makro.VERSION,
        name = "Makro",
        clientSideOnly = true,
        canBeDeactivated = true,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
        dependencies = "required-after:forgelin;"
)
@Suppress("MemberVisibilityCanBePrivate")
@Mod.EventBusSubscriber(modid = Makro.ID)
object Makro {

    const val ID = "makro"
    const val VERSION = "@VERSION@"

    val logger: Logger = LogManager.getLogger(ID)

    lateinit var keybindManager: KeybindManager
    lateinit var macroDispatcher: MacroDispatcher

    lateinit var configFolder: File

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
       configFolder = File(ModConfig.folder, ".makro")
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {

        keybindManager = KeybindManager(logger, File(configFolder, "keybinds.json"))
        macroDispatcher = MacroDispatcher(logger, File(ModConfig.folder))
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {

    }
}
