package heckerpowered.ultimate.client.proxy;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import heckerpowered.ultimate.common.proxy.UltimateServerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Client side proxy.
 *
 * @author Heckerpowered
 */
@SideOnly(Side.CLIENT)
public final class UltimateClientProxy extends UltimateServerProxy {

    @Override
    public void onPreInitialization(@Nonnull final FMLPreInitializationEvent event) {
        super.onPreInitialization(event);
    }

    @Override
    public void onInitialization(@Nonnull final FMLInitializationEvent event) {
        super.onInitialization(event);
    }

    @Override
    public void onPostInitialization(@Nonnull final FMLPostInitializationEvent event) {
        super.onPostInitialization(event);
    }

    @Override
    public EntityPlayer getPlayer(@Nonnull final MessageContext context) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return context.getServerHandler().playerEntity;
        }

        return Minecraft.getMinecraft().thePlayer;
    }
}
