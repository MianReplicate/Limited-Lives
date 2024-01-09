package net.goose.limitedlives.data.neoforge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.api.ILivesData;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@SuppressWarnings("unused")
public class ModCap {
    public static final EntityCapability<ILivesData, Void> LIVES_CAP =
            EntityCapability.createVoid(
                    new ResourceLocation(LimitedLives.MOD_ID, "lives_cap"),
                    ILivesData.class
            );

    @SubscribeEvent
    public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
        event.registerEntity(LIVES_CAP, EntityType.PLAYER, (myEntity, context) -> new LivesData(myEntity));
    }
}
