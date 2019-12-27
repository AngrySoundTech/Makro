package com.github.angrysoundtech.civmaster

import com.github.angrysoundtech.civmaster.client.Proxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
        modid = CivMaster.ID,
        version = CivMaster.VERSION,
        name = "CivMaster",
        clientSideOnly = true,
        canBeDeactivated = true,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
        dependencies = "required-after:forgelin;"
)
@Suppress("MemberVisibilityCanBePrivate")
object CivMaster {

    const val ID = "civmaster"
    const val VERSION = "@VERSION@"

    @SidedProxy(clientSide = "com.github.angrysoundtech.civmaster.client.Proxy")
    lateinit var proxy: Proxy

    val logger: Logger = LogManager.getLogger(ID)

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }
}
