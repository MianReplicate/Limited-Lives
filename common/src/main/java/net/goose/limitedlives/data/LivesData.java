package net.goose.limitedlives.data;

import com.mojang.authlib.GameProfile;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.goose.limitedlives.LimitedLives;
import net.goose.limitedlives.api.ILivesData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;

import javax.annotation.Nullable;
import java.util.Optional;

public class LivesData implements ILivesData {
    private final LivingEntity livingEntity;

    private int lives = LimitedLives.config.startingLives.get();

    public LivesData(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @ExpectPlatform
    public static Optional<LivesData> get(LivingEntity livingEntity) {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static Optional<LivesData> get(Entity entity) {
        throw new AssertionError();
    }

    @Override
    public LivingEntity getLivingEntity() {
        return this.livingEntity;
    }

    @Override
    public int getLives(){
        return this.lives;
    }

    @Override
    public void setLives(int lives) {
        if(!livingEntity.level.isClientSide){
            this.lives = lives;
        }
    }

    @Override
    public void refreshLives() {
        if(!livingEntity.level.isClientSide){
            ServerPlayer serverPlayer = (ServerPlayer) livingEntity;
            Component livesCount = Component.translatable("gui.limitedlives.lives_count", getLives());
            serverPlayer.sendSystemMessage(livesCount);

            if(this.lives <= 0){
                this.lives = LimitedLives.config.startingLives.get();

                MinecraftServer server = livingEntity.getServer();

                Component component = Component.translatable("bannedmessage.limitedlives.lost_all_lives");

                if(LimitedLives.config.bannedUponDeath.get()){
                    UserBanList userbanlist = server.getPlayerList().getBans();
                    serverPlayer.getGameProfile();
                    GameProfile gameprofile = serverPlayer.getGameProfile();

                    UserBanListEntry userbanlistentry = new UserBanListEntry(gameprofile, null, "Limitedlives", null, component == null ? null : component.getString());
                    userbanlist.add(userbanlistentry);

                    if (serverPlayer != null) {
                        serverPlayer.connection.disconnect(component);
                    }
                } else if(!serverPlayer.isSpectator()){
                    serverPlayer.setGameMode(GameType.SPECTATOR);
                    livingEntity.sendSystemMessage(component);
                }
            }
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("lives", getLives());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        setLives(tag.getInt("lives"));
    }
}
