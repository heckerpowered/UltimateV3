package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Inject(method = "onCriticalHit", at = @At("HEAD"), cancellable = true)
    public void onCriticalHit(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "onEnchantmentCritical", at = @At("HEAD"), cancellable = true)
    public void onEnchantmentCritical(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "setPlayerSPHealth", at = @At("HEAD"), cancellable = true)
    public void setPlayerSPHealth(float health, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }
}
