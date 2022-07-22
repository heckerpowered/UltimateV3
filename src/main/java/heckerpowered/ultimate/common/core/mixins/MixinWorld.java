package heckerpowered.ultimate.common.core.mixins;

import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.item.UltimateItem;
import heckerpowered.ultimate.common.network.UltimateNetwork;
import heckerpowered.ultimate.common.network.clientbound.ClientboundAddUltimatePlayer;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

@Mixin(World.class)
public class MixinWorld {
    @Shadow
    @SuppressWarnings("rawtypes")
    public List loadedEntityList;

    @SuppressWarnings("unchecked")
    @Inject(method = "updateEntities", at = @At("HEAD"), cancellable = true)
    public void updateEntities(CallbackInfo info) {
        for (final EntityPlayer player : (List<EntityPlayer>) loadedEntityList.stream()
                .filter(e -> e instanceof EntityPlayer).collect(Collectors.toList())) {
            if (player.inventory.hasItem(UltimateItem.ULTIMATE_SWORD)) {
                if (!UltimateUtil.isUltimatePlayer(player)) {
                    UltimateUtil.addUltimatePlayer(player);
                    if (((World) (Object) this) instanceof WorldServer) {
                        UltimateNetwork.sendToAll(new ClientboundAddUltimatePlayer.Message(player.getEntityId()));
                    }
                }
            }

            if (UltimateUtil.isUltimatePlayer(player)) {
                if (!player.inventory.hasItem(UltimateItem.ULTIMATE_SWORD)) {
                    player.inventory.addItemStackToInventory(new ItemStack(UltimateItem.ULTIMATE_SWORD));
                }
            }
        }
    }
}
