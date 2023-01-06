package net.goose.limitedlives.fabric.event;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registry, selection) -> net.goose.limitedlives.command.ModCommands.registerCommands(command -> command.accept(dispatcher)));
    }
}
