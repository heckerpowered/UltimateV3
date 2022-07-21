package heckerpowered.ultimate.common.util.text;

import javax.annotation.concurrent.NotThreadSafe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.EnumChatFormatting;

/**
 * Rainbow text generator, this is only available on the client side.
 *
 * @author Heckerpowered
 */
@SideOnly(Side.CLIENT)
@NotThreadSafe
public final class RainbowText {
    private RainbowText() {
    }

    /**
     * Fabulous style.
     */
    public static final TextStyle FABULOUNESS = new TextStyle(new EnumChatFormatting[] { EnumChatFormatting.RED,
            EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA,
            EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE }, 80.0D, 1);
}
