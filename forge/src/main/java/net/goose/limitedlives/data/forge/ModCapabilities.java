package net.goose.limitedlives.data.forge;

import net.goose.limitedlives.api.ILivesData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCapabilities {
    public static final Capability<ILivesData> LIVES_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<ILivesData>() {
    });

    public static class EventCapHandler {
        @SubscribeEvent
        public static void attachentityCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player) {
                LivesDataImpl.attach(event);
            }
        }

        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.register(ILivesData.class);
        }
    }
}
