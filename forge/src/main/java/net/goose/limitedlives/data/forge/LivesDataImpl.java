package net.goose.limitedlives.data.forge;

import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.api.ILivesData;
import net.goose.limitedlives.data.LivesData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LivesDataImpl {
    public static Optional<ILivesData> get(final LivingEntity entity) {
        return entity.getCapability(ModCapabilities.LIVES_DATA_CAPABILITY).resolve();
    }

    public static Optional<ILivesData> get(final Entity entity) {
        return entity.getCapability(ModCapabilities.LIVES_DATA_CAPABILITY).resolve();
    }
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        class HeartCapProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

            public static final ResourceLocation IDENTIFIER = new ResourceLocation(LimitedLives.MOD_ID, "lives_data");
            private final ILivesData backend = new LivesData((LivingEntity) event.getObject());
            private final LazyOptional<ILivesData> optionalData = LazyOptional.of(() -> backend);

            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return ModCapabilities.LIVES_DATA_CAPABILITY.orEmpty(cap, this.optionalData);
            }

            @Override
            public CompoundTag serializeNBT() {
                return this.backend.serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                this.backend.deserializeNBT(nbt);
            }
        }

        final HeartCapProvider provider = new HeartCapProvider();

        event.addCapability(HeartCapProvider.IDENTIFIER, provider);
    }
}
