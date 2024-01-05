package net.goose.limitedlives;

import fuzs.forgeconfigapiport.api.config.v3.ModConfigEvents;
import net.goose.limitedlives.configuration.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LimitedLives {
    public static final String MOD_ID = "limitedlives";

    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModConfig config;

    public static void init() {
        LOGGER.info("It's time to limit some lives!");

    }
}