package heckerpowered.ultimate.common.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * Ultimate's core mod.
 * @author Heckerpowered
 */
public final class UltimateCore implements IFMLLoadingPlugin {

    /**
     * Create a new ultimate core.
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public UltimateCore()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        //
        // Initialize mixin
        //
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.ultimate.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");

        Field field = LaunchClassLoader.class.getDeclaredField("transformers");
        field.setAccessible(true);
        field.set(Launch.classLoader,
                new UltimateTransformerList(Launch.classLoader.getTransformers()));
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.set(field, field.getModifiers() | Modifier.FINAL);
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
