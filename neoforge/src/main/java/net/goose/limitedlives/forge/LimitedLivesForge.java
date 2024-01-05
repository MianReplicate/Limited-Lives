package net.goose.limitedlives.forge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.configuration.ConfigHolder;
import net.goose.limitedlives.data.forge.ModCapabilities;
import net.goose.limitedlives.forge.event.EventHandler;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = LimitedLives.MOD_ID)
public class LimitedLivesForge {
    public LimitedLivesForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        LimitedLives.config = ConfigHolder.SERVER;
        LimitedLives.init();

        NeoForge.EVENT_BUS.register(EventHandler.class);
        NeoForge.EVENT_BUS.register(ModCapabilities.EventCapHandler.class);
    }
}