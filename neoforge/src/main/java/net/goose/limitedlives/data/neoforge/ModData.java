package net.goose.limitedlives.data.neoforge;

import com.mojang.serialization.Codec;
import net.goose.limitedlives.LimitedLives;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModData {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, LimitedLives.MOD_ID);
    public static final Supplier<AttachmentType<Integer>> LIVES_DATA = ATTACHMENT_TYPES.register(
            "lives", () -> AttachmentType.builder(() -> LimitedLives.config.startingLives.get()).serialize(Codec.INT).copyOnDeath().build());
}
