package net.goose.limitedlives.neoforge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.configuration.ConfigHolder;
import net.goose.limitedlives.data.neoforge.ModCap;
import net.goose.limitedlives.data.neoforge.ModData;
import net.goose.limitedlives.neoforge.event.EventHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = LimitedLives.MOD_ID)
public class LimitedLivesNeoForge {
    public LimitedLivesNeoForge(IEventBus eventBus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        LimitedLives.config = ConfigHolder.SERVER;
        LimitedLives.init();

        NeoForge.EVENT_BUS.register(EventHandler.class);
        ModData.ATTACHMENT_TYPES.register(eventBus);
        eventBus.register(ModCap.class);
    }
}