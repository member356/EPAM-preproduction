package com.epam.khrypushyna.task1.container;

import com.epam.khrypushyna.task1.entity.ChainletJewel;
import com.epam.khrypushyna.task1.entity.GemRingJewel;
import com.epam.khrypushyna.task1.entity.Jewel;
import com.epam.khrypushyna.task1.entity.RingJewel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class ItemsListTest {

    private ItemsList<Jewel> itemsList;

    @Before
    public void before() {
        itemsList = new ItemsList<>();
        itemsList.add(new RingJewel("gold", 800, true, 16));
        itemsList.add(new RingJewel("silver", 1200, true, 18));
        itemsList.add(new RingJewel("gold", 1950, true, 15));
        itemsList.add(new RingJewel("silver", 1800, true, 16));
        itemsList.add(new GemRingJewel("whitegold", 5300, true, 16, "brilliant"));
        itemsList.add(new GemRingJewel("gold", 4200, true, 17, "pearl"));
        itemsList.add(new GemRingJewel("whitegold", 7300, true, 16, "pearl"));
        itemsList.add(new ChainletJewel("silver", 1200, true, 42));
        itemsList.add(new ChainletJewel("gold", 3200, true, 48));
    }

    @Test
    public void shouldReturnSize() {
        assertEquals(9, itemsList.size());
    }

    @Test
    public void shouldReturnElementByIndex() {
        assertEquals(new RingJewel("gold", 800, true, 16), itemsList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnExceptionWhenGetElementByIndexMoreThanSize() {
        itemsList.get(12);
    }

    @Test
    public void shouldReturnTrueWhenAddElement() {
        assertTrue(itemsList.add(new RingJewel("gold", 6100, true, 19)));
    }

    @Test
    public void shouldReturnTrueWhenAddNull() {
        assertTrue(itemsList.add(null));
    }

    @Test
    public void shouldIncreaseListWhenAddElementByIndex() {
        int prevSize = itemsList.size();
        itemsList.add(2, new ChainletJewel("gold", 2500, true, 35));
        int newSize = itemsList.size();

        assertEquals(1, newSize - prevSize);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnExceptionWhenAddElementByIndexWhichIsOutOfSize() {
        itemsList.add(45, new ChainletJewel("gold", 2500, true, 35));
    }

    @Test
    public void shouldReturnTrueWhenRemoveElement() {
        assertTrue(itemsList.remove(new RingJewel("silver", 1200, true, 18)));
    }

    @Test
    public void shouldReturnElementWhenRemoveElementByIndex() {
        assertEquals(new RingJewel("gold", 1950, true, 15), itemsList.remove(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnExceptionWhenRemoveElementByIndexWhenIndexOutOfSize() {
        itemsList.remove(45);
    }

    @Test
    public void shouldIterateByCondition() {

        Iterator itr = itemsList.iterateWithCondition(new IteratorCondition() {
            @Override
            public boolean satisfiedBy(Object o) {
                return o.getClass().equals(RingJewel.class);
            }
        });

        ItemsList iteratedList = new ItemsList();
        while (itr.hasNext()) {
            Object o = itr.next();
            if (o != null) {
                iteratedList.add(o);
            }
        }

        System.out.println(iteratedList);
        assertTrue(!iteratedList.contains(new GemRingJewel("gold", 4200, true, 17, "pearl")));
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnExceptionWhenIterateConditionIsNull() {

        Iterator itr = itemsList.iterateWithCondition(null);

        ItemsList iteratedList = new ItemsList();
        while (itr.hasNext()) {
            Object o = itr.next();
            if (o != null) {
                iteratedList.add(o);
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void shouldCreateAnObjectWithNullCapacity() {
        new ItemsList(null);
    }

    @Test
    public void shouldReturnNegativeWhenIndexOfNotExistObject() {
        assertEquals(-1, itemsList.indexOf(new GemRingJewel()));
    }

    @Test
    public void shouldReturnTrueWhenRemoveAll() {
        ItemsList itl = new ItemsList();
        itl.add(new RingJewel("silver", 1200, true, 18));
        itl.add(new RingJewel("gold", 800, true, 16));

        assertTrue(itemsList.removeAll(itl));
    }

    @Test
    public void shouldReturnTrueWhenRetainAll() {
        ItemsList itl = new ItemsList();
        itl.add(new RingJewel("gold", 800, true, 16));

        assertTrue(itemsList.retainAll(itl));
    }

    @Test
    public void shouldReturnFalseWhenRetainInEmptyList(){
        itemsList.clear();
        ItemsList itl = new ItemsList();
        itl.add(new RingJewel("gold", 800, true, 16));

        assertFalse(itemsList.retainAll(itl));
    }

    @Test
    public void shouldReturnElementsThatSatisfyConditionOnIteratorNext() {
        ItemsList<Integer> integers = new ItemsList<>(asList(1, 2, 3, 4));

        Iterator<Integer> integerIterator = integers.iterateWithCondition(new IteratorCondition<Integer>() {
            @Override
            public boolean satisfiedBy(Integer o) {
                return o > 2;
            }
        });

        assertEquals(3, (int) integerIterator.next());
        assertEquals(4, (int) integerIterator.next());
    }
}