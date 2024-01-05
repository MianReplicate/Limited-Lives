package net.goose.limitedlives.data.forge;

import net.goose.limitedlives.api.ILivesData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

public class LivesDataImpl {
    public static Optional<ILivesData> get(final LivingEntity entity) {
        return Optional.ofNullable(entity.getCapability(ModCapabilities.LIVES_DATA));
    }

    public static Optional<ILivesData> get(final Entity entity) {
        return Optional.ofNullable(entity.getCapability(ModCapabilities.LIVES_DATA));
    }
}
