package net.goose.limitedlives.forge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.configuration.ConfigHolder;
import net.goose.limitedlives.data.forge.ModCapabilities;
import net.goose.limitedlives.forge.event.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(LimitedLives.MOD_ID)
public class LimitedLivesForge {
    public LimitedLivesForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        LimitedLives.config = ConfigHolder.SERVER;
        LimitedLives.init();

        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        MinecraftForge.EVENT_BUS.register(ModCapabilities.EventCapHandler.class);
    }
}