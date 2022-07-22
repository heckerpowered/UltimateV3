package heckerpowered.ultimate.common.proxy;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import heckerpowered.ultimate.common.UltimateMod;
import heckerpowered.ultimate.common.item.ItemUltimateSword;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Server side proxy.
 *
 * @author Heckerpowered
 */
public class UltimateServerProxy implements UltimateProxy {

    @Override
    public void onPreInitialization(@Nonnull final FMLPreInitializationEvent event) {
        GameRegistry.registerItem(
                new ItemUltimateSword(), ItemUltimateSword.NAME, UltimateMod.MODID);
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
