package heckerpowered.ultimate.common.network.clientbound;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import heckerpowered.ultimate.common.util.UltimateUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/**
 * A clientbound packet to add a player to the ultimate list.
 *
 * @author Heckerpowered
 */
public final class ClientboundAddUltimatePlayer
        implements IMessageHandler<ClientboundAddUltimatePlayer.Message, IMessage> {
    public static final class Message implements IMessage {
        private int id;

        public Message() {
        }

        public Message(final int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public void fromBytes(@Nonnull final ByteBuf buf) {
            id = buf.readInt();
        }

        @Override
        public void toBytes(@Nonnull final ByteBuf buf) {
            buf.writeInt(id);
        }
    }

    @Override
    public IMessage onMessage(@Nonnull final Message message, @Nonnull final MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            final Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.theWorld != null) {
                final Entity entity = minecraft.theWorld.getEntityByID(message.getId());
                if (entity != null && entity instanceof EntityPlayer) {
                    UltimateUtil.addUltimatePlayer((EntityPlayer) entity);
                }
            }
        }

        return null;
    }
}
