package net.goose.limitedlives.neoforge.event;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.command.ModCommands;
import net.goose.limitedlives.data.LivesData;
import net.goose.limitedlives.data.neoforge.ModData;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = LimitedLives.MOD_ID)

public class EventHandler {
    @SubscribeEvent
    public static void OnCommandsRegister(RegisterCommandsEvent event) {
        ModCommands.registerCommands(command -> command.accept(event.getDispatcher()));
    }
}
