package com.epam.khrypushyna.task2;

import com.epam.khrypushyna.task1.container.ItemsList;
import com.epam.khrypushyna.task1.entity.GemRingJewel;
import com.epam.khrypushyna.task1.entity.Jewel;
import com.epam.khrypushyna.task1.entity.RingJewel;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CopyOnWriteListTest {

    CopyOnWriteList<Jewel> copyOnWriteist;
    CopyOnWriteList<Integer> listItegers;

    CopyOnWriteList<Jewel> listForAddAll;
    CopyOnWriteList<String> listStrings;
    ItemsList<Jewel> extraList;

    @Before
    public void before(){

        copyOnWriteist = new CopyOnWriteList();
        copyOnWriteist.add(new RingJewel("gold", 800, true, 16));
        copyOnWriteist.add(new RingJewel("silver", 1200, true, 18));
        copyOnWriteist.add(new RingJewel("gold", 1950, true, 15));
        copyOnWriteist.add(new RingJewel("silver", 1800, true, 16));
        copyOnWriteist.add(new GemRingJewel("whitegold", 5300, true, 16, "brilliant"));
        copyOnWriteist.add(new GemRingJewel("gold", 4200, true, 17, "pearl"));

        listItegers = new CopyOnWriteList<>();
        listItegers.add(1);
        listItegers.add(2);
        listItegers.add(3);

        listForAddAll = new CopyOnWriteList();
        listForAddAll.add(new RingJewel("silver", 1200, true, 18));
        listForAddAll.add(new RingJewel("gold", 1900, true, 17));
        listForAddAll.add(new RingJewel("silver", 1400, true, 18));

        listStrings = new CopyOnWriteList<>();
        listStrings.add("BBB");

        extraList = new ItemsList<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNewValueAddingAndCallsNextOnOldListWithLessSize(){
        Iterator it = listStrings.iterator();
        listStrings.add("BBB");
        it.next();
        it.next();
    }

    @Test
    public void shouldIterateOldListWhenValueRemoving(){
        Iterator it = listStrings.iterator();
        listStrings.remove("BBB");
        assertEquals("BBB", it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldIterateOldListWhenValueSetting(){
        Iterator it = listStrings.iterator();
        listStrings.set(0, "HHH");
        it.next();
        it.next();
    }

    @Test
    public void shouldIterateArrayOnWhichIteratorCalled(){
        Iterator it = listItegers.iterator();
        listItegers.clear();
        assertEquals(1, it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenIterateClearList() {
        listItegers.clear();
        listItegers.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenAddAllAndCallsNextOnOldListWithLessSize(){
        Iterator itr = copyOnWriteist.iterator();

        copyOnWriteist.addAll(listForAddAll);
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void shouldIterateOldArrayWhenAddAllByIndex(){
        Iterator itr = copyOnWriteist.iterator();

        copyOnWriteist.addAll(1, listForAddAll);
        itr.next();
        itr.next();
        assertEquals(new RingJewel("gold", 1950, true, 15), itr.next());
    }

    @Test
    public void shouldReturnTrueWhenContainsAllElementsInList(){
        CopyOnWriteList c = new CopyOnWriteList();
        c.add(1);
        c.add(2);
        assertTrue(listItegers.containsAll(c));
    }

    @Test
    public void shouldIterateOldListWhenValuesRemoving(){
        extraList.add(new RingJewel("gold", 800, true, 16));

        Iterator it = copyOnWriteist.iterator();
        copyOnWriteist.removeAll(extraList);

        assertEquals(new RingJewel("gold", 800, true, 16), it.next());
    }

    @Test
    public void shouldIterateOldListWhenValuesRetaining(){
        extraList.add(new RingJewel("gold", 800, true, 16));

        Iterator it = copyOnWriteist.iterator();
        copyOnWriteist.retainAll(extraList);

        it.next();
        assertEquals(new RingJewel("silver", 1200, true, 18), it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNewValueAddingWithIndexAndIterateOldList(){
        Iterator it = listStrings.iterator();
        listStrings.add(0, "BBB");
        it.next();
        it.next();
    }

}