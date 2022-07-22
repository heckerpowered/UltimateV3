package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import heckerpowered.ultimate.common.core.impl.IMixinEntity;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

@Mixin(Entity.class)
public class MixinEntity implements IMixinEntity {
    private boolean ultimateDead;
    private int ultimateDeathTime;

    @Inject(method = "setDead", at = @At(value = "HEAD"), cancellable = true)
    private final void setDead(CallbackInfo info) {
        if (((Entity) (Object) this) instanceof EntityPlayer
                && UltimateUtil.isUltimatePlayer((EntityPlayer) (Object) this)) {
            info.cancel();
        }
    }

    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    public void setOnFireFromLava(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "setFire", at = @At("HEAD"), cancellable = true)
    public void setFire(int fire, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "kill", at = @At("HEAD"), cancellable = true)
    public void kill(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "updateFallState", at = @At("HEAD"), cancellable = true)
    public void updateFallState(double distanceFallenThisTick, boolean onGround, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "dealFireDamage", at = @At("HEAD"), cancellable = true)
    public void dealFireDamage(int amountDamage, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "isImmuneToFire", at = @At("HEAD"), cancellable = true)
    public final void isImmuneToFire(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "fall", at = @At("HEAD"), cancellable = true)
    public void fall(float distance, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "handleWaterMovement", at = @At("HEAD"), cancellable = true)
    public void handleWaterMovement(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "handleLavaMovement", at = @At("HEAD"), cancellable = true)
    public void handleLavaMovement(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "applyEntityCollision", at = @At("HEAD"), cancellable = true)
    public void applyEntityCollision(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this) || UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "addVelocity", at = @At("HEAD"), cancellable = true)
    public void addVelocity(double x, double y, double z, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "setBeenAttacked", at = @At("HEAD"), cancellable = true)
    public void setBeenAttacked(CallbackInfo info) {
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

    @Inject(method = "canBeCollidedWith", at = @At("HEAD"), cancellable = true)
    public void canBeCollidedWith(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isEntityAlive", at = @At("HEAD"), cancellable = true)
    public void isEntityAlive(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "mountEntity", at = @At("HEAD"), cancellable = true)
    public void mountEntity(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "setVelocity", at = @At("HEAD"), cancellable = true)
    public void setVelocity(double x, double y, double z, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "canBePushed", at = @At("HEAD"), cancellable = true)
    public void canBePushed(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isBurning", at = @At("HEAD"), cancellable = true)
    public void isBurning(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "hitByEntity", at = @At("HEAD"), cancellable = true)
    public void hitByEntity(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isEntityInvulnerable", at = @At("HEAD"), cancellable = true)
    public void isEntityInvulnerable(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(true);
        }
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "canRenderOnFire", at = @At("HEAD"), cancellable = true)
    public void canRenderOnFire(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isPushedByWater", at = @At("HEAD"), cancellable = true)
    public void isPushedByWater(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "setInWeb", at = @At("HEAD"), cancellable = true)
    public void setInWeb(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Override
    public boolean isUltimateDead() {
        return ultimateDead;
    }

    @Override
    public void setUltimateDead() {
        ultimateDead = true;
    }

    @Override
    public int getUltimateDeathTime() {
        return ultimateDeathTime;
    }

    @Override
    public void setUltimateDeathTime(final int ticks) {
        if (ticks > ultimateDeathTime) {
            ultimateDeathTime = ticks;
        }
    }
}
