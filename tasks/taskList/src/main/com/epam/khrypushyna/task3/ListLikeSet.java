package com.epam.khrypushyna.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.UnaryOperator;

public class ListLikeSet<E> extends ArrayList<E> {

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            return super.add(e);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void add(int index, E element) {
        if (!contains(element)) {
            super.add(index, element);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        final int before = size();
        checkDuplicates(c);
        super.addAll(c);
        return size() != before;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        final int before = size();
        checkDuplicates(c);
        super.addAll(index, c);
        return size() != before;
    }

    @Override
    public E set(int index, E element) {
        if (contains(element) && get(index) != element) {
            throw new IllegalArgumentException();
        }
        return super.set(index, element);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        ArrayList<E> list = new ArrayList<>(this);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            E element = (E) it.next();
            E ret = operator.apply(element);

            if (list.contains(ret)) {
                throw new IllegalArgumentException();
            }
            list.set(list.indexOf(element), ret);
        }
        super.replaceAll(operator);
    }

    private void checkDuplicates(Collection<? extends E> c) {
        for (Object o : c) {
            if (contains(o)) {
                throw new IllegalArgumentException();
            }
        }
    }
}
