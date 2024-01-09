package net.goose.limitedlives.data.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.api.ILivesData;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class LivesDataImpl extends LivesData implements ComponentV3 {
    private int lives = LimitedLives.config.startingLives.get();
    public LivesDataImpl(LivingEntity livingEntity) {
        super(livingEntity);
    }

    public static Optional<LivesData> get(LivingEntity livingEntity) {
        try {
            return Optional.of(ModComponents.LIVES_DATA.get(livingEntity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<LivesData> get(Entity entity) {
        try {
            return Optional.of(ModComponents.LIVES_DATA.get(entity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static int getLives(LivesData livesData) {
       return ((LivesDataImpl) livesData).lives;
    }

    public static void setLives(LivesData livesData, int lives) {
        ((LivesDataImpl) livesData).lives = lives;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag nbt = this.serializeNBT();
        for (String key : nbt.getAllKeys()) {
            tag.put(key, Objects.requireNonNull(nbt.get(key)));
        }
    }
}