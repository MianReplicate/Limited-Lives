package net.goose.limitedlives.api;

import net.goose.limitedlives.util.Serializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;


public interface ILivesData extends Serializable<CompoundTag> {

    LivingEntity getLivingEntity();

    int getLives();

    void setLives(int lives);

    void refreshLives();
}


