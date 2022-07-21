package heckerpowered.ultimate.common.core;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import heckerpowered.ultimate.common.core.transformers.MinecraftTransformer;

/**
 * Ultimate's core mod.
 */
public final class UltimateCore implements IFMLLoadingPlugin {

    /**
     * Get asm transformer classes.
     */
    @Nonnull
    @Override
    public String[] getASMTransformerClass() {
        return new String[] { MinecraftTransformer.class.getName() };

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

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
