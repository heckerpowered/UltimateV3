package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "setDead", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setDead()V"), cancellable = true)
    private final void setDead(CallbackInfo info) {

    }
}
