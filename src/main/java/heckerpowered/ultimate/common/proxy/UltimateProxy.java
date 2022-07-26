package heckerpowered.ultimate.common.proxy;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * A sided proxy for the mod, used to separate the client and server code,
 * The initialization is split into three phases, pre-initialization, init and
 * post-initialization.
 *
 * @author Heckerpowered
 * @see SidedProxy
 */
public interface UltimateProxy {

    /**
     * Called when forge pre-initialization.
     *
     * @param event {@link FMLPreInitializationEvent}
     */
    public void onPreInitialization(@Nonnull final FMLPreInitializationEvent event);

    /**
    * Called when forge initialization.
    *
    * @param event {@link FMLInitializationEvent}
    */
    public void onInitialization(@Nonnull final FMLInitializationEvent event);

    /**
     * Called when forge post-initialization.
     *
     * @param event {@link FMLPostInitializationEvent}
     */
    public void onPostInitialization(@Nonnull final FMLPostInitializationEvent event);

    /**
     * Get the player from the message context.
     *
     * @param context {@link MessageContext}
     * @return The {@link EntityPlayer} who sent the message.
     */
    public EntityPlayer getPlayer(@Nonnull final MessageContext context);
}
