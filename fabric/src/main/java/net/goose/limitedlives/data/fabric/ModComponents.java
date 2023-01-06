package net.goose.limitedlives.data.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.goose.limitedlives.LimitedLives;
import net.minecraft.resources.ResourceLocation;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<LivesDataImpl> LIVES_DATA =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation(LimitedLives.MOD_ID, "lives_data"), LivesDataImpl.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        // Add the component to every PlayerEntity instance
        registry.registerForPlayers(LIVES_DATA, LivesDataImpl::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}