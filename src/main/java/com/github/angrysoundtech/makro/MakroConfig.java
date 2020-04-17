package com.github.angrysoundtech.makro;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public class MakroConfig {

    public static class Client {

        public final ForgeConfigSpec.ConfigValue<String> folder;
        public final ForgeConfigSpec.BooleanValue devMode;

        public Client(ForgeConfigSpec.Builder builder) {
            // TODO: Handle this exception better
            try {

                folder = builder
                        .comment("Macro root folder")
                        .define("folder", Minecraft.getInstance().gameDir.getCanonicalPath() + File.separator + "macros");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create config!");
            }

            devMode = builder
                    .comment("Enable development mode. Macros recompile every run")
                    .define("devMode", false);
        }
    }

    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
}
