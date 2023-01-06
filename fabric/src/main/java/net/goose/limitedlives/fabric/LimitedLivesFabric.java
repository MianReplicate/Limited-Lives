package net.goose.limitedlives.limitedlives.fabric;

import net.goose.limitedlives.limitedlives.LimitedLives;
import net.fabricmc.api.ModInitializer;

public class LimitedLivesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LimitedLives.init();
    }
}