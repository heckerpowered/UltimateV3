package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.EntityLivingBase;
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
}
