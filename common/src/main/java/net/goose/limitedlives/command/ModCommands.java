package net.goose.limitedlives.command;

import com.mojang.brigadier.CommandDispatcher;
import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.command.commands.LimitedLivesCommand;
import net.minecraft.commands.CommandSourceStack;

import java.util.function.Consumer;

public class ModCommands {
    public static void registerCommands(Consumer<Consumer<CommandDispatcher<CommandSourceStack>>> command) {
        LimitedLives.LOGGER.debug("Registering ModCommands for " + LimitedLives.MOD_ID);
        command.accept(LimitedLivesCommand::register);
    }
}
