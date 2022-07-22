package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {
    @Inject(method = "isMovementBlocked", at = @At("HEAD"), cancellable = true)
    public void isMovementBlocked(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "collideWithPlayer", at = @At("HEAD"), cancellable = true)
    public void collideWithPlayer(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this) || UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onDeath(DamageSource damageSource, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    public void attackEntityFrom(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "canAttackPlayer", at = @At("HEAD"), cancellable = true)
    public void canAttackPlayer(EntityPlayer player, CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "damageEntity", at = @At("HEAD"), cancellable = true)
    public void damageEntity(DamageSource damageSource, float amount, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"), cancellable = true)
    public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(targetEntity)) {
            info.cancel();
        }
    }

    @Inject(method = "setDead", at = @At(value = "HEAD"), cancellable = true)
    public void setDead(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "fall", at = @At("HEAD"), cancellable = true)
    public void fall(float distance, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "onKillEntity", at = @At("HEAD"), cancellable = true)
    public void onKillEntity(EntityLivingBase entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "setInWeb", at = @At("HEAD"), cancellable = true)
    public void setInWeb(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }
}
