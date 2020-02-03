package com.github.angrysoundtech.makro

import com.github.angrysoundtech.makro.client.Proxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.input.Keyboard

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

    @SidedProxy(clientSide = "com.github.angrysoundtech.makro.client.Proxy")
    lateinit var proxy: Proxy

    val logger: Logger = LogManager.getLogger(ID)

    lateinit var keybindManager: KeybindManager

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        keybindManager = KeybindManager()
        proxy.init(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }
}
