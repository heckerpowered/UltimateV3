package heckerpowered.ultimate.common.core;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * Ultimate's core mod.
 * @author Heckerpowered
 */
public final class UltimateCore implements IFMLLoadingPlugin {

    /**
     * Create a new ultimate core.
     */
    public UltimateCore() {
        //
        // Initialize mixin
        //
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.ultimate.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }

    /**
     * Get asm transformer classes.
     */
    @Nonnull
    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    /**
     * Get the coremod's container
     */
    @Nullable
    @Override
    public String getModContainerClass() {
        return null;
    }

    /**
     * Get the setup class
     */
    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    /**
     * Get inject data
     */
    @Override
    public void injectData(Map<String, Object> data) {

    }

    /**
     * Get access transformers
     */
    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
