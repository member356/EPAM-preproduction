package com.epam.khrypushyna.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CopyOnWriteList<E> implements List<E> {

    private Object[] array;

    public CopyOnWriteList() {
        array = new Object[0];
    }

    public CopyOnWriteList(Collection<? extends E> c) {
        this.array = c.toArray();
    }

    @Override
    public boolean add(E e) {
        final int oldLength = array.length;
        add(array.length, e);
        return array.length != oldLength;
    }

    @Override
    public void add(int index, E element) {
        if (correctIndex(index)) {
            final Object[] oldArray = array;
            array = new Object[array.length + 1];

            System.arraycopy(oldArray, 0, array, 0, index);
            array[index] = element;

            System.arraycopy(oldArray, index, array, index + 1, oldArray.length - index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    @Override
    public void clear() {
        array = new Object[0];
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        Object[] arr = c.toArray();

        if (arr.length == 0)
            return false;

        int len = array.length;
        if (len == 0 && arr.getClass() == Object[].class)
            array = arr;
        else {
            Object[] newElements = Arrays.copyOf(array, len + arr.length);
            System.arraycopy(arr, 0, newElements, len, arr.length);
            array = newElements;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] cs = c.toArray();

        int len = array.length;
        if (index > len || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index +
                    ", Size: " + len);
        if (cs.length == 0)
            return false;
        int numMoved = len - index;
        Object[] newElements;
        if (numMoved == 0)
            newElements = Arrays.copyOf(array, len + cs.length);
        else {
            newElements = new Object[len + cs.length];
            System.arraycopy(array, 0, newElements, 0, index);
            System.arraycopy(array, index,
                    newElements, index + cs.length,
                    numMoved);
        }
        System.arraycopy(cs, 0, newElements, index, cs.length);
        array = newElements;
        return true;

    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (indexOf(e) < 0)
                return false;
        }
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < array.length; i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < array.length; i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {
        int cursor;
        final private Object[] content;

        public Iter() {
            this.content = array;
        }

        public boolean hasNext() {
            return cursor != content.length;
        }

        public E next() {
            int i = cursor;
            if (i >= content.length)
                throw new NoSuchElementException();
            cursor = i + 1;
            return (E) content[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = array.length - 1; i >= 0; i--)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = array.length - 1; i >= 0; i--)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
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
    public boolean remove(Object o) {
        final int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E remove(int index) {
        if (correctIndex(index) && array.length > 0) {
            final Object[] oldArray = array;

            array = new Object[array.length - 1];
            System.arraycopy(oldArray, 0, array, 0, index);
            System.arraycopy(oldArray, index + 1, array, index, oldArray.length - index - 1);
            return (E) oldArray[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        } else {
            return removeSeveral(c, false);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        } else {
            return removeSeveral(c, true);
        }
    }


    @Override
    public E set(int index, E element) {
        if (correctIndex(index)) {
            final Object[] oldArray = array;

            array = Arrays.copyOf(oldArray, oldArray.length);

            final E oldElement = (E) array[index];
            array[index] = element;
            return oldElement;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, array.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < array.length) {
            return (T[]) Arrays.copyOf(array, array.length, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, array.length);
        if (a.length > array.length)
            a[array.length] = null;
        return a;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;
        final Iterator<E> e1 = iterator();
        final Iterator e2 = ((List) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }


    private boolean correctIndex(int index) {
        return index >= 0 && index <= array.length;
    }

    private boolean removeSeveral(Collection<?> c, boolean contain) {
        Object[] oldArray = array;
        int oldArrayLength = oldArray.length;
        if (oldArrayLength != 0) {
            int i = 0;
            Object[] content = new Object[oldArrayLength];
            int k = 0;

            while (true) {
                if (k >= oldArrayLength) {
                    if (i != oldArrayLength) {
                        this.array = Arrays.copyOf(content, i);
                        return true;
                    }
                    break;
                }
                Object tmp = oldArray[k];

                if (c.contains(tmp) == contain) {
                    content[i++] = tmp;
                }
                ++k;
            }
        }
        return false;
    }

}