package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(EntityLivingBase.class)
public final class MixinEntityLivingBase {
    private final EntityLivingBase self = (EntityLivingBase) (Object) this;

    @Inject(method = "getHealth", at = @At("HEAD"), cancellable = true)
    public void getHealth(CallbackInfoReturnable<Float> info) {
        if (UltimateUtil.isUltimatePlayer((EntityPlayer) self)) {
            info.setReturnValue(20.0F);
        }
    }

    @Inject(method = "getMaxHealth", at = @At("HEAD"), cancellable = true)
    public void getMaxHealth(CallbackInfoReturnable<Float> info) {
        if (UltimateUtil.isUltimatePlayer((EntityPlayer) self)) {
            info.setReturnValue(20.0F);
        }
    }
}
