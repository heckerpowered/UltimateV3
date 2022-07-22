package heckerpowered.ultimate.common.core.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.chunk.Chunk;

@Mixin(Chunk.class)
public class MixinChunk {
    @Inject(method = "removeEntity", at = @At("HEAD"), cancellable = true)
    public void removeEntity(Entity entity, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "removeEntityAtIndex", at = @At("HEAD"), cancellable = true)
    public void removeEntityAtIndex(Entity entity, int index, CallbackInfo info) {
        if (UltimateUtil.isUltimatePlayer(entity)) {
            info.cancel();
        }
    }

    @Inject(method = "getEntitiesWithinAABBForEntity", at = @At("TAIL"), cancellable = true)
    public void getEntitiesWithinAABBForEntity(Entity entity, AxisAlignedBB boundingBox, List<Entity> entities,
            IEntitySelector selector, CallbackInfo info) {
        entities.removeIf(UltimateUtil::isUltimatePlayer);
    }

    @Inject(method = "getEntitiesOfTypeWithinAAAB", at = @At("TAIL"), cancellable = true)
    public void getEntitiesOfTypeWithinAAAB(Class<?> type, AxisAlignedBB boundingBox, List<Entity> entities,
            IEntitySelector selector, CallbackInfo info) {
        entities.removeIf(UltimateUtil::isUltimatePlayer);
    }
}
