package net.goose.limitedlives.command.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class LimitedLivesCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("ll")
                        .then(Commands.literal("get-lives")
                                .requires((commandSource) -> commandSource.hasPermission(Commands.LEVEL_ALL))
                                .executes((command) -> getLives(command.getSource()))
                                .then(Commands.argument("Player", EntityArgument.entity())
                                        .requires((commandSource) -> commandSource.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                        .executes((command) -> getLives(command.getSource(), EntityArgument.getEntity(command, "Player"))))
                        )
                        .then(Commands.literal("set-lives")
                                .requires((commandSource) -> commandSource.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .then(Commands.argument("Amount", IntegerArgumentType.integer())
                                        .executes((command) -> setLives(command.getSource(), IntegerArgumentType.getInteger(command, "Amount"))))
                                .then(Commands.argument("Player", EntityArgument.entity())
                                        .then(Commands.argument("Amount", IntegerArgumentType.integer())
                                                .executes((command) -> setLives(command.getSource(), EntityArgument.getEntity(command, "Player"), IntegerArgumentType.getInteger(command, "Amount")))))));
    }
    private static int getLives(CommandSourceStack source) throws CommandSyntaxException {
        if (source.isPlayer()) {
            LivingEntity playerthatsentcommand = source.getPlayer();
            LivesData.get(playerthatsentcommand).ifPresent(livesData -> livesData.refreshLives());
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int getLives(CommandSourceStack source, Entity chosenentity) throws CommandSyntaxException {

        LivingEntity playerthatsentcommand = source.getPlayer();

        if (!source.isPlayer()) {
            LivesData.get(chosenentity).ifPresent(livesData -> LimitedLives.LOGGER.info(chosenentity.getName().getString() + " has " + livesData.getLives() + " lives."));
        } else {
            LivesData.get(chosenentity).ifPresent(livesData -> playerthatsentcommand.sendSystemMessage(Component.translatable(chosenentity.getName().getString() + " has " + livesData.getLives() + " lives.")));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int setLives(CommandSourceStack source, int amount) throws CommandSyntaxException {
        if (source.isPlayer()) {
            LivingEntity playerthatsentcommand = source.getPlayer();
            LivesData.get(playerthatsentcommand).ifPresent(livesData -> {
                livesData.setLives(amount);
                livesData.refreshLives();
            });
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int setLives(CommandSourceStack source, Entity chosenentity, int amount) throws CommandSyntaxException {

        LivingEntity playerthatsentcommand = source.getPlayer();

        LivesData.get(chosenentity).ifPresent(livesData -> {
            livesData.setLives(amount);
            livesData.refreshLives();
        });

        if (chosenentity != playerthatsentcommand && source.isPlayer()) {
            playerthatsentcommand.sendSystemMessage(Component.translatable("Set " + chosenentity.getName().getString() + "'s Lives to " + amount));
        } else if (!source.isPlayer()) {
            LimitedLives.LOGGER.info("Set " + chosenentity.getName().getString() + "'s Lives difference to " + amount);
        }

        return Command.SINGLE_SUCCESS;
    }
}
