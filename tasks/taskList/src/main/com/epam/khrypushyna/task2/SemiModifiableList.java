package com.epam.khrypushyna.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SemiModifiableList<E> implements List<E> {

    private final List<E> unmodifiableList;
    private final List<E> modifiableList;

    public SemiModifiableList(List<E> unmodifiableList, List<E> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;

        if (this.unmodifiableList == null || this.modifiableList == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int size() {
        return unmodifiableList.size() + modifiableList.size();
    }

    @Override
    public boolean isEmpty() {
        return unmodifiableList.isEmpty() && modifiableList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!c.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {
        Iterator<E> it;
        boolean workWithUnmodifiable;

        Iter() {
            it = unmodifiableList.iterator();
            workWithUnmodifiable = true;
        }

        @Override
        public boolean hasNext() {
            boolean ret = it.hasNext();
            if (ret == false && workWithUnmodifiable) {

                it = modifiableList.iterator();
                workWithUnmodifiable = false;
                return it.hasNext();
            }
            return ret;
        }

        @Override
        public E next() {
            hasNext();
            return it.next();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] summaryList = new Object[unmodifiableList.size() + modifiableList.size()];

        System.arraycopy(unmodifiableList.toArray(), 0, summaryList, 0, unmodifiableList.size());
        System.arraycopy(modifiableList.toArray(), 0, summaryList, unmodifiableList.size(), modifiableList.size());
        return summaryList;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        final int size = size();

        if (a.length < size) {
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }
        System.arraycopy(toArray(), 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        return modifiableList.add(e);
    }

    @Override
    public void add(int index, E element) {
        if (correctIndex(index)) {
            if (index >= unmodifiableList.size()) {
                modifiableList.add(index - unmodifiableList.size(), element);
            } else {
                throw new IllegalArgumentException("Index is less than unmod-List size");
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return modifiableList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        final int prevSize = size();
        if (correctIndex(index)) {
            if (index >= unmodifiableList.size()) {
                modifiableList.addAll(index - unmodifiableList.size(), c);
            } else {
                throw new IllegalArgumentException("Index is less than unmod-List size");
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return prevSize != size();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean noOneInUnmodifiableList = true;
        for (Object o : c) {
            if (unmodifiableList.contains(o)) {
                noOneInUnmodifiableList = false;
                break;
            }
        }
        if (noOneInUnmodifiableList) {
            return modifiableList.removeAll(c);
        } else {
            throw new IllegalArgumentException("One of the elements is in the unmodifiable list");
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.containsAll(unmodifiableList)) {
            return modifiableList.retainAll(c);
        } else {
            throw new IllegalArgumentException("One of the elements in NOT in the unmodifable list");
        }
    }

    @Override
    public void clear() {
        if (unmodifiableList.isEmpty()) {
            modifiableList.clear();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public E get(int index) {
        if (correctIndex(index)) {
            if (index < unmodifiableList.size()) {
                return unmodifiableList.get(index);
            } else {
                return modifiableList.get(index - unmodifiableList.size());
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        if (index >= unmodifiableList.size()) {
            return modifiableList.set(index - unmodifiableList.size(), element);
        } else {
            throw new IllegalArgumentException("Index is less than unmodifable-List size");
        }
    }

    @Override
    public E remove(int index) {
        if (index > unmodifiableList.size()) {
            return modifiableList.remove(index - unmodifiableList.size());
        } else {
            throw new IllegalArgumentException("Removing from unmodifiable list is not supported");
        }
    }

    @Override
    public boolean remove(Object o) {
        if (modifiableList.contains(o) && !unmodifiableList.contains(o)) {
            return modifiableList.remove(o);
        } else {
            throw new IllegalArgumentException("Removing from unmodifiable list is not supported");
        }
    }

    @Override
    public int indexOf(Object o) {
        if (unmodifiableList.contains(o)) {
            return unmodifiableList.indexOf(o);
        }
        if (modifiableList.contains(o)) {
            return modifiableList.indexOf(o) + unmodifiableList.size();
        } else {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        final int indexMod = modifiableList.lastIndexOf(o);
        if (indexMod >= 0) {
            return indexMod + unmodifiableList.size();
        }
        final int indexUnmod = unmodifiableList.lastIndexOf(o);
        if (indexUnmod >= 0) {
            return indexUnmod;
        } else {
            return -1;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "SemiModifiableList " + Arrays.toString(toArray());
    }


    private boolean correctIndex(int index) {
        return index >= 0 && index < size();
    }
}
