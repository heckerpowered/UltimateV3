package heckerpowered.ultimate.common.core;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

/**
 * Ultimate fake player, used to hide players.
 *
 * @author Heckerpowered
 */
public final class UltimateFakePlayer extends FakePlayer {
    /**
     * Map of all active fake player usernames to their entities
     */
    private static Map<GameProfile, UltimateFakePlayer> fakePlayers = new HashMap<>();

    public UltimateFakePlayer(@Nonnull final WorldServer world, @Nonnull final GameProfile name) {
        super(world, name);
    }

    @Override
    public void closeScreen() {

    }

    public static UltimateFakePlayer getFakePlayer(@Nonnull final EntityPlayerMP player) {
        final GameProfile profile = player.getGameProfile();
        if (fakePlayers.containsKey(profile)) {
            return fakePlayers.get(profile);
        }

        final UltimateFakePlayer fakePlayer = new UltimateFakePlayer(player.getServerForPlayer(), profile);
        fakePlayers.put(profile, fakePlayer);
        return fakePlayer;
    }
}