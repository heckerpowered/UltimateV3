package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;

@Mixin(EntityLivingBase.class)
public final class MixinEntityLivingBase {
    private final EntityLivingBase self = (EntityLivingBase) (Object) this;

    @Inject(method = "getHealth", at = @At("HEAD"), cancellable = true)
    public void getHealth(CallbackInfoReturnable<Float> info) {
        if (UltimateUtil.isUltimatePlayer(self)) {
            info.setReturnValue(20.0F);
        }
    }

    @Inject(method = "getMaxHealth", at = @At("HEAD"), cancellable = true)
    public void getMaxHealth(CallbackInfoReturnable<Float> info) {
        if (UltimateUtil.isUltimatePlayer(self)) {
            info.setReturnValue(20.0F);
        }
    }

    @Inject(method = "damageEntity", at = @At("HEAD"), cancellable = true)
    public void damageEntity(DamageSource damageSource, float amount, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(self)) {
            info.cancel();
        }
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    public void attackEntityFrom(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(self)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    public void setHealth(float health, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(self)) {
            info.cancel();
        }
    }

    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;onLivingUpdate", remap = false))
    public boolean onUpdate(EntityLivingBase entityLivingBase) {
        if (UltimateUtil.isUltimatePlayer(entityLivingBase)) {
            return false;
        }

        return ForgeHooks.onLivingUpdate(entityLivingBase);
    }

    @Inject(method = "updateFallState", at = @At("HEAD"), cancellable = true)
    public void updateFallState(double distanceFallenThisTick, boolean onGround, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "canBreatheUnderwater", at = @At("HEAD"), cancellable = true)
    public void canBreatheUnderwater(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "onDeathUpdate", at = @At("HEAD"), cancellable = true)
    public void onDeathUpdate(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "addPotionEffect", at = @At("HEAD"), cancellable = true)
    public void addPotionEffect(PotionEffect effect, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    public void onDeath(DamageSource damageSource, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "knockBack", at = @At("HEAD"), cancellable = true)
    public void knockBack(Entity entity, float x, double y, double z, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    public void getDeathSound(CallbackInfoReturnable<String> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue("");
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    public void getHurtSound(CallbackInfoReturnable<String> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue("");
        }
    }

    @Inject(method = "isEntityAlive", at = @At("HEAD"), cancellable = true)
    public void isEntityAlive(CallbackInfoReturnable<Boolean> info) {
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

    @SideOnly(Side.CLIENT)
    @Inject(method = "performHurtAnimation", at = @At("HEAD"), cancellable = true)
    public void performHurtAnimation(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "handleHealthUpdate", at = @At("HEAD"), cancellable = true)
    public void handleHealthUpdate(byte type, CallbackInfo info) {
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

    @Inject(method = "isMovementBlocked", at = @At("HEAD"), cancellable = true)
    public void isMovementBlocked(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "collideWithEntity", at = @At("HEAD"), cancellable = true)
    protected void collideWithEntity(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }

    @Inject(method = "canBeCollidedWith", at = @At("HEAD"), cancellable = true)
    public void canBeCollidedWith(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "canBePushed", at = @At("HEAD"), cancellable = true)
    public void canBePushed(CallbackInfoReturnable<Boolean> info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "setBeenAttacked", at = @At("HEAD"), cancellable = true)
    public void setBeenAttacked(CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }
}
