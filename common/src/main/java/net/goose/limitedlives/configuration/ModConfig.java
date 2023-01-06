package net.goose.limitedlives.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public final ForgeConfigSpec.IntValue startingLives;
    public final ForgeConfigSpec.IntValue amountOfLivesLost;
    public final ForgeConfigSpec.BooleanValue bannedUponDeath;

    public ModConfig(final ForgeConfigSpec.Builder builder) {
        builder.comment("It's recommended to edit the config BEFORE you make/play a world. While editing the config in an already generated world can work sometimes, there may be visual bugs or just bugs in general.");
        builder.comment("This category holds general values that will mostly be customized by most.");
        builder.push("Starting Configurations");
        this.startingLives = buildInt(builder, "Starting Lives:", 10, 1, Integer.MAX_VALUE, "This is how many lives you'll start out with in the world.");
        this.amountOfLivesLost = buildInt(builder, "Amount of Lives Lost Per Death:", 1, 1, Integer.MAX_VALUE, "This is how many lives you'll lose every death.");
        this.bannedUponDeath = buildBoolean(builder, "Banned Upon Dying:", false, "This value determines whether you get banned or go into spectator mode when you lose all your lives.");
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ForgeConfigSpec.DoubleValue buildDouble(ForgeConfigSpec.Builder builder, String name, double defaultValue, double min, double max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ForgeConfigSpec.ConfigValue buildString(ForgeConfigSpec.Builder builder, String name, String defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

}