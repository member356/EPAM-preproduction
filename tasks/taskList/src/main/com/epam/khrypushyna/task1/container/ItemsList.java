package com.epam.khrypushyna.task1.container;


import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ItemsList<E> implements List<E> {

    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private int modCount = 0;

    public ItemsList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public ItemsList(int capacity) {
        this.array = new Object[capacity];
    }

    public ItemsList(Collection<? extends E> c) {
        this.array = c.toArray();
        size = array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ItemsList.this.array;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            try {
                ItemsList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

    public Iterator<E> iterateWithCondition(IteratorCondition con) {
        return new CustomIterator<>(con);
    }

    private class CustomIterator<E> implements Iterator<E> {

        IteratorCondition condition;
        int indexOfNext;
        int lastElReturned;
        int expectedModCount = modCount;
        private Object nextObject;
        private int currentPosition;

        CustomIterator(IteratorCondition con) {
            this.condition = con;
            this.lastElReturned = -1;
            this.nextObject = null;
        }

        public E next() {
            if (modCount != expectedModCount || indexOfNext >= array.length)
                throw new ConcurrentModificationException();

            if (nextObject != null) {
                lastElReturned = currentPosition - 1;
                final E ret = (E) nextObject;
                nextObject = null;
                return ret;
            } else {
                hasNext();
                lastElReturned = currentPosition - 1;
                final E ret = (E) nextObject;
                nextObject = null;
                return ret;
            }
        }

        public boolean hasNext() {
            if (nextObject == null) {
                for (int i = currentPosition; i < size; ++i) {
                    if (condition.satisfiedBy(array[i])) {
                        nextObject = array[i];
                        currentPosition = i + 1;
                        return true;
                    }
                }
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, size);
    }

    @Override
    public boolean add(E e) {
        int minCapacity = size + 1;
        if (minCapacity - array.length > 0) {
            array = Arrays.copyOf(array, minCapacity + DEFAULT_CAPACITY);
        }

        array[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0) {
                        System.arraycopy(array, index + 1, array, index, numMoved);
                    }
                    array[--size] = null;
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(array[index])) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0) {
                        System.arraycopy(array, index + 1, array, index, numMoved);
                    }
                    array[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E oldValue = (E) array[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;

        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object oldValue = array[index];
        array[index] = element;
        return (E) oldValue;
    }

    @Override
    public void add(int index, E element) {
        int minCapacity = size + 1;

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (minCapacity - array.length > 0) {
            array = Arrays.copyOf(array, minCapacity + DEFAULT_CAPACITY);
        }

        System.arraycopy(array, index, array, index + 1, size - index);

        array[index] = element;
        size++;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] list = c.toArray();

        for (Object object : list) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {

        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c != null) {
            return removeSeveral(c, true);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c != null) {
            return removeSeveral(c, false);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        Object[] a = c.toArray();

        int numNew = a.length;
        int minCapacity = size + numNew;

        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        if (minCapacity - array.length > 0) {
            array = Arrays.copyOf(array, minCapacity + DEFAULT_CAPACITY);
        }

        System.arraycopy(a, 0, array, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        Object[] a = c.toArray();
        int numNew = a.length;
        int minCapacity = size + numNew;
        int numMoved = size - index;

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        if (minCapacity - array.length > 0) {
            array = Arrays.copyOf(array, minCapacity + DEFAULT_CAPACITY);
        }
        if (numMoved > 0) {
            System.arraycopy(array, index, array, index + numNew, numMoved);
        }

        System.arraycopy(a, 0, array, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    private boolean removeSeveral(Collection<?> c, boolean contain) {

        final Object[] array = this.array;
        int read = 0;
        int write = 0;

        for (; read < size; read++) {
            if (c.contains(array[read]) == contain) {
                array[write++] = array[read];
            }
        }
        if (write != size) {
            for (int i = write; i < size; i++) {
                array[i] = null;
            }
            size = write;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(Arrays.toString(array));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (o instanceof List) {
            List itemsList = (List) o;
            if (size != itemsList.size()) return false;
            for (Object el : this.array) {
                if (!itemsList.contains(el))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(array);
        return result;
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
}
