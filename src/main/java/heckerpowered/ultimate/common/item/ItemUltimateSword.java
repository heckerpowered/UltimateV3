package heckerpowered.ultimate.common.item;

import javax.annotation.Nonnull;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import heckerpowered.ultimate.common.UltimateMod;
import heckerpowered.ultimate.common.util.text.RainbowText;
import net.minecraft.entity.player.EntityPlayer;
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
     * Define the item's name.
     *
     * @see GameRegistry#registerItem(Item, String, String)
     */
    public static final String NAME = "ultimate_sword";

    /**
     * Define the item's full id in a common place for everything to reference.
     */
    public static final String PATH = UltimateMod.resource(NAME).toString();

    /**
     * Create a new ultimate sword.
     */
    public ItemUltimateSword() {
        super(EnumHelper.addToolMaterial("ultimate", Integer.MAX_VALUE, Integer.MAX_VALUE,
                Float.MAX_VALUE, Float.MAX_VALUE, Integer.MAX_VALUE));
        setUnlocalizedName(NAME).setTextureName(PATH);
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

    @Override
    public void setDamage(ItemStack stack, int damage) {
        //
        // Do nothing
        //
    }

    @Override
    public int getDamage(ItemStack stack) {
        return 0;
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

    /**
     * Prevent the item from being dropped.
     */
    @Override
    public boolean onDroppedByPlayer(@Nonnull final ItemStack item, @Nonnull final EntityPlayer player) {
        return false;
    }
}
