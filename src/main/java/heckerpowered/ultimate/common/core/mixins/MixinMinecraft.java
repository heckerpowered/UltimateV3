package heckerpowered.ultimate.common.core.mixins;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public final class MixinMinecraft {
    @ModifyArg(method = "startGame", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", remap = false))
    private final String setTitle(@Nonnull final String title) {
        return "Minecraft 1.7.10 | Ultimate V3";
    }
}
