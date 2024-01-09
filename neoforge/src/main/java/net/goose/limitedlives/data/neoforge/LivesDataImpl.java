package net.goose.limitedlives.data.neoforge;

import net.goose.limitedlives.api.ILivesData;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

@SuppressWarnings("unused")
public class LivesDataImpl {
    public static Optional<ILivesData> get(final LivingEntity entity) {
        return Optional.ofNullable(entity.getCapability(ModCap.LIVES_CAP));
    }

    public static Optional<ILivesData> get(final Entity entity) {
        return Optional.ofNullable(entity.getCapability(ModCap.LIVES_CAP));
    }

    public static int getLives(LivesData livesData) {
        return livesData.getLivingEntity().getData(ModData.LIVES_DATA.get());
    }

    public static void setLives(LivesData livesData, int lives) {
        livesData.getLivingEntity().setData(ModData.LIVES_DATA.get(), lives);
    }
}
