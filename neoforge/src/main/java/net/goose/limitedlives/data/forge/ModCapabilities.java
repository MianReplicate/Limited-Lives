package net.goose.limitedlives.data.forge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.api.ILivesData;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {
    public static final EntityCapability<ILivesData, Void> LIVES_DATA =
            EntityCapability.createVoid(
                    new ResourceLocation(LimitedLives.MOD_ID, "lives_data"),
                    ILivesData.class
            );

    public static class EventCapHandler {
        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.registerEntity(LIVES_DATA, EntityType.PLAYER, (myEntity, context) -> new LivesData(myEntity));
        }
    }
}
