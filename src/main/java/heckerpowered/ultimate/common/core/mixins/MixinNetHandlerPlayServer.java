package heckerpowered.ultimate.common.core.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.UltimateMod;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

@Mixin(NetHandlerPlayServer.class)
public class MixinNetHandlerPlayServer {
    @Shadow
    public EntityPlayerMP playerEntity;

    @Inject(method = "kickPlayerFromServer", at = @At("HEAD"), cancellable = true)
    public void kickPlayerFromServer(String reason, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(playerEntity)) {
            info.cancel();
            UltimateMod.LOGGER.info("Attemp to kick ultimate player \"{}\", reason \"{}\"", playerEntity, reason);
        }
    }
}
