package heckerpowered.ultimate.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import heckerpowered.ultimate.common.UltimateMod;

/**
 * Ultimate mod's items.
 *
 * @author Heckerpowered
 */
public final class UltimateItem {
    /**
     * Prevent instantiation.
     */
    private UltimateItem() {
    }

    public static final ItemUltimateSword ULTIMATE_SWORD = (ItemUltimateSword) GameRegistry.registerItem(
            new ItemUltimateSword(), ItemUltimateSword.NAME, UltimateMod.MODID);
}
