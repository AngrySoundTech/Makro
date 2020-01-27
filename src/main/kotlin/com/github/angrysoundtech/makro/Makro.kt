package com.github.angrysoundtech.makro

import com.github.angrysoundtech.makro.api.Macro
import com.github.angrysoundtech.makro.client.Proxy
import com.github.angrysoundtech.makro.scriptengine.KtsObjectLoader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import org.lwjgl.input.Keyboard
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.launch

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

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        ClientRegistry.registerKeyBinding(debugKey)
        proxy.init(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }

    fun fireTheTest() {
        val scriptReader = Files.newBufferedReader(Paths.get("macros/test.kts"))

        setIdeaIoUseFallback()

        GlobalScope.launch {
            println(measureTimeMillis {
                val loadedObj: Macro = KtsObjectLoader().load(scriptReader)

                loadedObj.run()
            })
        }
    }

    val debugKey = KeyBinding("DEBUG KEY", Keyboard.KEY_V, "Makro")

    @SubscribeEvent
    fun onKeyPress(event: InputEvent.KeyInputEvent) {
        if (debugKey.isPressed) {
            fireTheTest()
        }
    }
}
