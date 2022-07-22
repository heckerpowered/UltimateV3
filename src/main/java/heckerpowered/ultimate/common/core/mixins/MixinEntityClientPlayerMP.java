package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.client.entity.EntityClientPlayerMP;

@Mixin(EntityClientPlayerMP.class)
public class MixinEntityClientPlayerMP {
    @Inject(method = "setPlayerSPHealth", at = @At("HEAD"), cancellable = true)
    public void setPlayerSPHealth(float health, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(this)) {
            info.cancel();
        }
    }
}
