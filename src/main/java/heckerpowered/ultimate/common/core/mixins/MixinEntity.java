package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.core.impl.IMixinEntity;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

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
