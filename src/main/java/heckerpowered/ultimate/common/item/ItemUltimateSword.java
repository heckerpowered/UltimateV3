package heckerpowered.ultimate.common.item;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import heckerpowered.ultimate.common.UltimateMod;
import heckerpowered.ultimate.common.util.text.RainbowText;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Ultimate sword.
 *
 * @author Heckerpowered
 */
public final class ItemUltimateSword extends ItemSword {

    /**
     * Define the item id in a common place for everything to reference.
     *
     * @see GameRegistry#registerItem(Item, String, String)
     */
    public static final String NAME = UltimateMod.resource("ultimate_sword").toString();

    /**
     * Create a new ultimate sword.
     */
    public ItemUltimateSword() {
        super(EnumHelper.addToolMaterial("ultimate", Integer.MAX_VALUE, Integer.MAX_VALUE,
                Float.MAX_VALUE, Float.MAX_VALUE, Integer.MAX_VALUE));
        setUnlocalizedName(NAME).setTextureName(NAME);
    }

    /**
     * Check whether the item is damageable.
     */
    @Override
    public boolean isDamageable() {
        //
        // Make ultimate sword undamageable.
        //
        return false;
    }

    /**
     * Determine whether the stack should render enchant effects.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(@Nonnull final ItemStack stack) {
        return true;
    }

    /**
     * Get the ultimate sword's display name.
     */
    @Override
    public String getItemStackDisplayName(@Nonnull final ItemStack stack) {
        return RainbowText.FABULOUNESS.format(super.getItemStackDisplayName(stack));
    }
}
