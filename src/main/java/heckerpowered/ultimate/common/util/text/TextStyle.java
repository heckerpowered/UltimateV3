package heckerpowered.ultimate.common.util.text;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

/**
 * Text style.
 *
 * @author Heckerpowered
 * @see RainbowText
 */
public final class TextStyle {
    /**
     * Inner text style.
     */
    private final EnumChatFormatting[] style;

    /**
     * Delay between colors.
     */
    private final double delay;

    /**
     * The position step.
     */
    private final int posstep;

    /**
     * Create a new text style.
     *
     * @param style Inner text style.
     * @param delay Delay between colors.
     * @param posstep The position step.
     */
    public TextStyle(@Nonnull final EnumChatFormatting[] style, @Nonnegative final double delay,
            @Nonnegative int posstep) {
        this.style = style;
        this.delay = Math.max(delay, 0.001D);
        this.posstep = posstep;
    }

    /**
     * Get the inner style.
     *
     * @return Text style.
     */
    public final EnumChatFormatting[] getStyle() {
        return style;
    }

    /**
     * Get the delay between colors.
     *
     * @return Delay between colors.
     */
    public final double getDelay() {
        return delay;
    }

    /**
     * Get the position step.
     *
     * @return Position step.
     */
    public final int getPosstep() {
        return posstep;
    }

    /**
    * Make a rainbow text with supplied style.
    *
    * @param input Input string.
    * @param style Style.
    * @param delay Delay between colors.
    * @param posstep The position step.
    * @return Rainbow text string.
    */
    public final String format(@Nonnull final String input) {
        StringBuffer stringBuffer = new StringBuffer(input.length() * 3);

        //
        // Caculate offset
        //
        final int offset = (int) Math.floor(Minecraft.getSystemTime() / delay) % style.length;

        //
        // Generate rainbow text.
        //
        for (int i = 0; i < input.length(); i = -~i) {
            final char c = input.charAt(i);

            final int col = (i * posstep + style.length - offset) % style.length;

            stringBuffer.append(style[col].toString());
            stringBuffer.append(c);
        }

        return stringBuffer.toString();
    }
}
