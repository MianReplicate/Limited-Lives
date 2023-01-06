package net.goose.limitedlives.limitedlives.forge;

import net.goose.limitedlives.limitedlives.LimitedLives;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LimitedLives.MOD_ID)
public class LimitedLivesForge {
    public LimitedLivesForge() {
        LimitedLives.init();
    }
}