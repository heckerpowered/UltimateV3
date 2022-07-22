package heckerpowered.ultimate.common.core.mixins;

import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.core.UltimateEntityList;
import heckerpowered.ultimate.common.item.UltimateItem;
import heckerpowered.ultimate.common.network.UltimateNetwork;
import heckerpowered.ultimate.common.network.clientbound.ClientboundAddUltimatePlayer;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

@Mixin(World.class)
public class MixinWorld {
    @Shadow
    @Final
    @Mutable
    private List<Entity> loadedEntityList;

    @Inject(method = "updateEntities", at = @At("HEAD"), cancellable = true)
    public void updateEntities(CallbackInfo info) {
        if (!(loadedEntityList instanceof UltimateEntityList)) {
            loadedEntityList = new UltimateEntityList(loadedEntityList);
        }

        for (final EntityPlayer player : loadedEntityList.stream()
                .filter(e -> e instanceof EntityPlayer).map(e -> (EntityPlayer) e).collect(Collectors.toList())) {
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
