package com.github.angrysoundtech.makro

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Config(modid = Makro.ID)
@Config.LangKey("makro.config.title")
object ModConfig {

    @JvmField
    @Config.Comment("Should the macros be compiled on startup? If not, lazily.")
    @Config.LangKey("makro.config.compileonstart")
    var compileOnStart = true

    fun update() {
        ConfigManager.sync(Makro.ID, Config.Type.INSTANCE)
    }

    @Mod.EventBusSubscriber(modid = Makro.ID)
    private object EventHandler {

        @SubscribeEvent
        fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
            if (event.modID == Makro.ID) {
                ModConfig.update()
            }
        }
    }
}
