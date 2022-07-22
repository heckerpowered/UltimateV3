package heckerpowered.ultimate.common.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

import heckerpowered.ultimate.common.util.StackWalker;
import heckerpowered.ultimate.common.util.UltimateUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public final class UltimateEntityList extends ArrayList<Entity> {
    public UltimateEntityList(Collection<Entity> c) {
        super(c);
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Entity) {
            if (UltimateUtil.isUltimatePlayer((Entity) o)) {
                return false;
            }
        }
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.removeIf(UltimateUtil::isUltimatePlayer);
        return super.removeAll(c);
    }

    @Override
    public Entity remove(int index) {
        Entity entity = super.get(index);
        if (UltimateUtil.isUltimatePlayer(entity)) {
            return entity;
        }

        return super.remove(index);
    }

    @Override
    public boolean add(Entity e) {
        if (UltimateUtil.isUltimateDead(e)) {
            return false;
        }

        return super.add(e);
    }

    @Override
    public void add(int index, Entity element) {
        if (UltimateUtil.isUltimateDead(element)) {
            return;
        }

        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends Entity> c) {
        c.removeIf(UltimateUtil::isUltimateDead);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Entity> c) {
        c.removeIf(UltimateUtil::isUltimateDead);
        return super.addAll(index, c);
    }

    @Override
    public Entity get(int index) {
        Entity entity = super.get(index);
        if (UltimateUtil.isUltimatePlayer(entity)) {
            Class<?> callerClass = StackWalker.getCallerClass(4);
            if (callerClass != World.class) {
                if (entity.worldObj instanceof WorldServer) {
                    return UltimateFakePlayer.getFakePlayer((EntityPlayerMP) entity);
                }
            }
        }

        return entity;
    }

    @Override
    public Iterator<Entity> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<Entity> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<Entity> listIterator(int index) {
        return new ListItr(index);
    }

    private class Itr implements Iterator<Entity> {
        int cursor; // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        Itr() {
        }

        public boolean hasNext() {
            if (cursor != size()) {
                if (UltimateUtil.isUltimatePlayer(next())) {
                    return false;
                } else {
                    cursor--;
                }
            }

            return cursor != size();
        }

        public Entity next() {
            checkForComodification();
            int i = cursor;
            cursor = i + 1;
            return UltimateEntityList.this.get(lastRet = i);
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                UltimateEntityList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends Itr implements ListIterator<Entity> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public Entity previous() {
            checkForComodification();
            int i = cursor - 1;
            cursor = i;
            return UltimateEntityList.this.get(lastRet = i);
        }

        public void set(Entity e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                UltimateEntityList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Entity e) {
            checkForComodification();

            try {
                int i = cursor;
                UltimateEntityList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
