package net.goose.limitedlives.forge.event;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.command.ModCommands;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = LimitedLives.MOD_ID)

public class EventHandler {
    @SubscribeEvent
    public static void OnCommandsRegister(RegisterCommandsEvent event) {
        ModCommands.registerCommands(command -> command.accept(event.getDispatcher()));
    }

    @SubscribeEvent
    public static void playerCloneEvent(final PlayerEvent.Clone event) {

        LivingEntity oldPlayer = event.getOriginal();
        oldPlayer.reviveCaps();
        LivingEntity newPlayer = event.getEntity();

        LivesData.get(oldPlayer).ifPresent(oldLives -> LivesData.get(newPlayer).ifPresent(livesData ->
                {
                    livesData.setLives(oldLives.getLives());
                    livesData.refreshLives();
                }
        ));

        oldPlayer.invalidateCaps();
    }
}
