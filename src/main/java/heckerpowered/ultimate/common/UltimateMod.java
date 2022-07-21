package heckerpowered.ultimate.common;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import heckerpowered.ultimate.common.proxy.UltimateProxy;
import net.minecraft.util.ResourceLocation;

/**
 * Declare that this is a mod with modId {@link UltimateMod#MODID}, name {@link UltimateMod#NAME},
 * version {@link UltimateMod#VERSION} and dependency on FML.
 *
 * @author Heckerpowered
 */
@Mod(modid = UltimateMod.MODID, name = UltimateMod.NAME, version = UltimateMod.VERSION, dependencies = "required-after:FML", acceptedMinecraftVersions = UltimateMod.ACCEPTABLE_VERSION, acceptableRemoteVersions = UltimateMod.ACCEPTABLE_VERSION, acceptableSaveVersions = UltimateMod.ACCEPTABLE_VERSION)
public final class UltimateMod {

    /**
     * Define mod id in a common place for everything to reference
     */
    public static final String MODID = "ultimate";

    /**
     * Define a user friendly name for the mod.
     */
    public static final String NAME = "Ultimate Mod";

    /**
     * Define a version for the mod.
     */
    public static final String VERSION = "3.0.0.0";

    /**
     * Directly reference a slf4j logger.
     */
    public static final Logger LOGGER = LogManager.getLogger("Ultimate");

    /**
     * @see Mod#acceptedMinecraftVersions()
     * @see Mod#acceptableRemoteVersions()
     * @see Mod#acceptableSaveVersions()
     */
    public static final String ACCEPTABLE_VERSION = "1.7.10";

    /**
     * The ultimate mod's instance.
     */
    private static final UltimateMod INSTANCE = new UltimateMod();

    @SidedProxy(clientSide = "heckerpowered.ultimate.client.proxy.UltimateClientProxy", serverSide = "heckerpowered.ultimate.common.proxy.UltimateServerProxy")
    @Nonnull
    private static UltimateProxy proxy;

    /**
     * Create a new instance of the mod.
     */
    private UltimateMod() {
        LOGGER.info("Loading {} version {}, build with forge version 10.13.4.1614", NAME, VERSION);
    }

    /**
     * Get the ultimate mod's instance.
     * @return
     */
    @Mod.InstanceFactory
    public static final UltimateMod getInstance() {
        return INSTANCE;
    }

    /**
     * Called when forge pre-initialization.
     *
     * @param event {@link FMLPreInitializationEvent}
     */
    @Mod.EventHandler
    public final void onPreInitialization(@Nonnull final FMLPreInitializationEvent event) {
        LOGGER.info("{} pre-initializating", NAME);
        proxy.onPreInitialization(event);
    }

    /**
     * Called when forge initialization.
     *
     * @param event {@link FMLInitializationEvent}
     */
    @Mod.EventHandler
    public final void onInitialization(@Nonnull final FMLInitializationEvent event) {
        LOGGER.info("{} initializating", NAME);
        proxy.onInitialization(event);
    }

    /**
     * Called when forge post-initialization.
     *
     * @param event {@link FMLPostInitializationEvent}
     */
    @Mod.EventHandler
    public final void onPostInitialization(@Nonnull final FMLPostInitializationEvent event) {
        LOGGER.info("{} post-initializating", NAME);
        proxy.onPostInitialization(event);
    }

    /**
     * Create a new resource location.
     *
     * @param path The path of the resource.
     * @return A new resource location {@link #MODID}:path.
     */
    public static final ResourceLocation resource(@Nonnull final String path) {
        return new ResourceLocation(MODID, path);
    }
}
