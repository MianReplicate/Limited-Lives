package net.goose.limitedlives.fabric;

import net.goose.limitedlives.LimitedLives;
import net.fabricmc.api.ModInitializer;
import net.goose.limitedlives.configuration.ConfigHolder;
import net.goose.limitedlives.event.ModCommands;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class LimitedLivesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModLoadingContext.registerConfig(LimitedLives.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        LimitedLives.config = ConfigHolder.SERVER;
        LimitedLives.init();
        ModCommands.register();
    }
}