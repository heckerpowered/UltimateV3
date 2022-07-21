package heckerpowered.ultimate.common.network;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import heckerpowered.ultimate.common.UltimateMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

/**
 * Ultimate mod's networking.
 *
 * @author Heckerpowered
 */
public final class UltimateNetwork {
    private static final SimpleNetworkWrapper NETWOR_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("ULTIMATE");

    /**
     * Prevent instantiation.
     */
    private UltimateNetwork() {
    }

    /**
     * Initialize ultimate mod's networking,
     * register packets.
     */
    public static final void initialize() {
    }

    /**
     * Encodes an Object[] of data into a DataOutputStream.
     *
     * @param dataValues an Object[] of data to encode
     * @param out the output stream to write to
     */
    public static final void encode(@Nonnull final Object[] dataValues, @Nonnull final ByteBuf out) {
        for (Object data : dataValues) {
            encode(out, data, data.getClass());
        }
    }

    /**
     * Encode an Object of data into a DataOutputStream
     *
     * @param data an Object of data to encode
     * @param out the output stream to write to
     */
    public static final void encode(@Nonnull final Object data, @Nonnull final ByteBuf out) {
        encode(out, data, data.getClass());
    }

    /**
     * Encode an Object of data into a DataOutputStream
     *
     * @param out The output stream to write to
     * @param data The data to encode
     * @param type The type of data to encode
     */
    private static final void encode(@Nonnull final ByteBuf out, @Nonnull final Object data,
            @Nonnull final Class<?> type) {
        if (type.isPrimitive() /* Moderately improve performance (?) */ ) {
            encodePrimitive(out, data, type);
        } else {
            encodeReference(out, data, type);
        }
    }

    /**
     * Encode a reference type of data into a DataOutputStream
     *
     * @param out The output stream to write to
     * @param data The data to encode
     * @param type The type of data to encode
     */
    private static final void encodeReference(@Nonnull final ByteBuf out, @Nonnull final Object data,
            @Nonnull final Class<?> type) {
        if (data instanceof String) {
            ByteBufUtils.writeUTF8String(out, (String) data);
        } else if (data instanceof ItemStack) {
            ByteBufUtils.writeItemStack(out, (ItemStack) data);
        } else {
            UltimateMod.LOGGER.fatal("Un-encodable data passed to encode(): {}", data);
        }
    }

    /**
     * Encode a primitive type of data into a DataOutputStream
     *
     * @param out The output stream to write to
     * @param data The data to encode
     * @param type The type of data to encode
     */
    private static final void encodePrimitive(@Nonnull final ByteBuf out, @Nonnull final Object data,
            @Nonnull final Class<?> type) {
        //
        // int.class equals Integer.class (?)
        //
        if (Byte.TYPE.isAssignableFrom(type)) {
            out.writeByte((byte) data);
        } else if (Integer.TYPE.isAssignableFrom(type)) {
            out.writeInt((int) data);
        } else if (Short.TYPE.isAssignableFrom(type)) {
            out.writeShort((short) data);
        } else if (Long.TYPE.isAssignableFrom(type)) {
            out.writeLong((long) data);
        } else if (Boolean.TYPE.isAssignableFrom(type)) {
            out.writeBoolean((boolean) data);
        } else if (Double.TYPE.isAssignableFrom(type)) {
            out.writeDouble((double) data);
        } else if (Float.TYPE.isAssignableFrom(type)) {
            out.writeFloat((float) data);
        } else {
            UltimateMod.LOGGER.error("Unknow primitive class type:{},data:{}", type, data);
        }
    }

    public static final EntityPlayer getPlayer(@Nonnull final MessageContext context) {
        return UltimateMod.getProxy().getPlayer(context);
    }

    /**
     * Send this message to the specified player.
     * The {@link IMessageHandler} for this message type should be on the CLIENT
     * side.
     *
     * @param message The message to send
     * @param player The player to send it to
     */
    public static final void sendTo(@Nonnull final IMessage message, @Nonnull final EntityPlayerMP player) {
        NETWOR_WRAPPER.sendTo(message, player);
    }

    /**
     * Send this message to everyone.
     * The {@link IMessageHandler} for this message type should be on the CLIENT
     * side.
     *
     * @param message The message to send
     */
    public static final void sendToAll(@Nonnull final IMessage message) {
        NETWOR_WRAPPER.sendToAll(message);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * The {@link IMessageHandler} for this message type should be on the CLIENT
     * side.
     *
     * @param message The message to send
     * @param point   The {@link TargetPoint} around which to send
     */
    public static final void sendToAllAround(@Nonnull final IMessage message,
            @Nonnull final NetworkRegistry.TargetPoint point) {
        NETWOR_WRAPPER.sendToAllAround(message, point);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * The {@link IMessageHandler} for this message type should be on the CLIENT
     * side.
     *
     * @param message     The message to send
     * @param dimensionId The dimension id to target
     */
    public static final void sendToDimension(@Nonnull final IMessage message, final int dimensionId) {
        NETWOR_WRAPPER.sendToDimension(message, dimensionId);
    }

    /**
     * Send this message to the server.
     * The {@link IMessageHandler} for this message type should be on the SERVER
     * side.
     *
     * @param message The message to send
     */
    public static final void sendToServer(@Nonnull final IMessage message) {
        NETWOR_WRAPPER.sendToServer(message);
    }
}
