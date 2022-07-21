package heckerpowered.ultimate.common.proxy;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import heckerpowered.ultimate.common.UltimateMod;
import heckerpowered.ultimate.common.item.UltimateItem;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Server side proxy.
 *
 * @author Heckerpowered
 */
public class UltimateServerProxy implements UltimateProxy {

    @Override
    public void onPreInitialization(@Nonnull final FMLPreInitializationEvent event) {
        UltimateMod.LOGGER.info("Loading item {}", UltimateItem.ULTIMATE_SWORD);
    }

    @Override
    public void onInitialization(@Nonnull final FMLInitializationEvent event) {

    }

    @Override
    public void onPostInitialization(@Nonnull final FMLPostInitializationEvent event) {

    }

    @Override
    public EntityPlayer getPlayer(@Nonnull final MessageContext context) {
        return context.getServerHandler().playerEntity;
    }

}
