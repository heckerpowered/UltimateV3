package heckerpowered.ultimate.common.util;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

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

    public static final boolean isUltimatePlayer(@Nonnull final EntityPlayer player) {
        return PLAYERS.contains(player.getCommandSenderName());
    }

    public static final void addUltimatePlayer(@Nonnull final EntityPlayer player) {
        PLAYERS.add(player.getCommandSenderName());
    }
}
