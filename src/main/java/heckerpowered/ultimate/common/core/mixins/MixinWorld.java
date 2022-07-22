package heckerpowered.ultimate.common.core.mixins;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.ultimate.common.core.UltimateEntityList;
import heckerpowered.ultimate.common.item.UltimateItem;
import heckerpowered.ultimate.common.network.UltimateNetwork;
import heckerpowered.ultimate.common.network.clientbound.ClientboundAddUltimatePlayer;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

@Mixin(World.class)
public class MixinWorld {
    @Shadow
    @Final
    @Mutable
    private List<Entity> loadedEntityList;

    @Inject(method = "updateEntities", at = @At("HEAD"), cancellable = true)
    public void updateEntities(CallbackInfo info) {
        if (!(loadedEntityList instanceof UltimateEntityList)) {
            loadedEntityList = new UltimateEntityList(loadedEntityList);
        }

        for (final EntityPlayer player : loadedEntityList.stream()
                .filter(e -> e instanceof EntityPlayer).map(e -> (EntityPlayer) e).collect(Collectors.toList())) {
            if (player.inventory.hasItem(UltimateItem.ULTIMATE_SWORD)) {
                if (!UltimateUtil.isUltimatePlayer(player)) {
                    UltimateUtil.addUltimatePlayer(player);
                    if (((World) (Object) this) instanceof WorldServer) {
                        UltimateNetwork.sendToAll(new ClientboundAddUltimatePlayer.Message(player.getEntityId()));
                    }
                }
            }

            if (UltimateUtil.isUltimatePlayer(player)) {
                if (!player.inventory.hasItem(UltimateItem.ULTIMATE_SWORD)) {
                    player.inventory.addItemStackToInventory(new ItemStack(UltimateItem.ULTIMATE_SWORD));
                }
            }
        }
    }

    @Inject(method = "removeEntity", at = @At("HEAD"), cancellable = true)
    public void removeEntity(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "removePlayerEntityDangerously", at = @At("HEAD"), cancellable = true)
    public void removePlayerEntityDangerously(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "onEntityRemoved", at = @At("HEAD"), cancellable = true)
    public void onEntityRemoved(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Redirect(method = "updateEntities", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;isDead:Z"))
    public boolean redirect$isDead$updateEntities(Entity e) {
        if (UltimateUtil.isUltimatePlayer(e)) {
            return false;
        }

        return e.isDead;
    }

    @Inject(method = "getEntitiesWithinAABBExcludingEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
    public void getEntitiesWithinAABBExcludingEntity(@Nullable Entity entityIn, AxisAlignedBB bb,
            CallbackInfoReturnable<List<Entity>> info) {
        List<Entity> returnValue = info.getReturnValue();
        returnValue.removeIf(UltimateUtil::isUltimatePlayer);
        info.setReturnValue(returnValue);
    }

    @Inject(method = "getEntitiesWithinAABBExcludingEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/AxisAlignedBB;Lnet/minecraft/command/IEntitySelector;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
    public void getEntitiesWithinAABBExcludingEntity(@Nullable Entity entityIn, AxisAlignedBB boundingBox,
            @Nullable IEntitySelector selector, CallbackInfoReturnable<List<Entity>> info) {
        List<Entity> returnValue = info.getReturnValue();
        returnValue.removeIf(UltimateUtil::isUltimatePlayer);
        info.setReturnValue(returnValue);
    }

    @Inject(method = "getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
    public <T extends Entity> void getEntitiesWithinAABB(Class<? extends T> classEntity, AxisAlignedBB bb,
            CallbackInfoReturnable<List<T>> info) {
        List<T> returnValue = info.getReturnValue();
        returnValue.removeIf(UltimateUtil::isUltimatePlayer);
        info.setReturnValue(returnValue);
    }

    @Inject(method = "selectEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/AxisAlignedBB;Lnet/minecraft/command/IEntitySelector;)Ljava/util/List;", at = @At("TAIL"), cancellable = true)
    public <T extends Entity> void selectEntitiesWithinAABB(Class<? extends T> clazz, AxisAlignedBB aabb,
            @Nullable IEntitySelector filter, CallbackInfoReturnable<List<T>> info) {
        List<T> returnValue = info.getReturnValue();
        returnValue.removeIf(UltimateUtil::isUltimatePlayer);
        info.setReturnValue(returnValue);
    }

    @Inject(method = "findNearestEntityWithinAABB", at = @At("TAIL"), cancellable = true)
    public void findNearestEntityWithinAABB(Class<Entity> entityType, AxisAlignedBB aabb,
            Entity closestTo, CallbackInfoReturnable<Entity> info) {
        if (UltimateUtil.isUltimatePlayer(info.getReturnValue())) {
            info.setReturnValue(null);
        }
    }

    @Inject(method = "getClosestVulnerablePlayerToEntity", at = @At("TAIL"), cancellable = true)
    public void getClosestVulnerablePlayerToEntity(Entity entity, double distance,
            CallbackInfoReturnable<EntityPlayer> info) {
        if (UltimateUtil.isUltimatePlayer(info.getReturnValue())) {
            info.setReturnValue(null);
        }
    }

    @Inject(method = "getClosestVulnerablePlayer", at = @At("TAIL"), cancellable = true)
    public void getClosestVulnerablePlayer(double x, double y, double z,
            double distance, CallbackInfoReturnable<EntityPlayer> info) {
        if (UltimateUtil.isUltimatePlayer(info.getReturnValue())) {
            info.setReturnValue(null);
        }
    }

    @Inject(method = "getPlayerEntityByName", at = @At("TAIL"), cancellable = true)
    public void getPlayerEntityByName(String name, CallbackInfoReturnable<EntityPlayer> info) {
        if (UltimateUtil.isUltimatePlayer(info.getReturnValue())) {
            info.setReturnValue(null);
        }
    }
}
