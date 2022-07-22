package heckerpowered.ultimate.common.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.spongepowered.asm.mixin.transformer.Proxy;

import cpw.mods.fml.common.asm.transformers.ModAPITransformer;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * A list in order to resort the transformers.
 *
 * @author Heckerpowered
 */
public final class UltimateTransformerList extends ArrayList<IClassTransformer>
        implements Comparator<IClassTransformer> {
    public UltimateTransformerList() {
        super();
    }

    public UltimateTransformerList(Collection<IClassTransformer> c) {
        super(c);
    }

    @Override
    public boolean add(IClassTransformer e) {
        boolean result = super.add(e);
        sort(this);
        return result;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Proxy) {
            return false;
        }

        return super.remove(o);
    }

    @Override
    public int compare(IClassTransformer o1, IClassTransformer o2) {
        if (ModAPITransformer.class == o1.getClass()) {
            return 1;
        }
        if (ModAPITransformer.class == o2.getClass()) {
            return -1;
        }

        if (o1 instanceof Proxy) {
            return 1;
        }
        if (o2 instanceof Proxy) {
            return -1;
        }

        return 0;
    }
}
