package heckerpowered.ultimate.common.util;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

import heckerpowered.ultimate.common.core.impl.IMixinEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Ultimate util
 */
public final class UltimateUtil {
    /**
     * Prevent instantiation.
     */
    private UltimateUtil() {
    }

    private static final Set<String> PLAYERS = new HashSet<>();

    public static final boolean isUltimatePlayer(@Nonnull final Entity entity) {
        return entity instanceof EntityPlayer && isUltimatePlayer((EntityPlayer) entity);
    }

    public static final boolean isUltimatePlayer(@Nonnull final Object player) {
        return player instanceof EntityPlayer && isUltimatePlayer((EntityPlayer) player);
    }

    public static final boolean isUltimatePlayer(@Nonnull final EntityPlayer player) {
        return PLAYERS.contains(player.getCommandSenderName());
    }

    public static final void addUltimatePlayer(@Nonnull final EntityPlayer player) {
        PLAYERS.add(player.getCommandSenderName());
    }

    public static final boolean isUltimateDead(@Nonnull final Entity entity) {
        return ((IMixinEntity) entity).isUltimateDead();
    }

    public static final void setUltimateDead(@Nonnull final Entity entity) {
        ((IMixinEntity) entity).setUltimateDead();
    }

    public static final int getUltimateDeathTime(@Nonnull final Entity entity) {
        return ((IMixinEntity) entity).getUltimateDeathTime();
    }

    public static final void setUltimateDeathTime(@Nonnull final Entity entity, final int ticks) {
        ((IMixinEntity) entity).setUltimateDeathTime(ticks);
    }
}
