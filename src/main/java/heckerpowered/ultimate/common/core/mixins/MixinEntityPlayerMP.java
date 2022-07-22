package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;

@Mixin(EntityPlayerMP.class)
public class MixinEntityPlayerMP {
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
}
