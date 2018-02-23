package com.epam.khrypushyna.task2;

import com.epam.khrypushyna.task1.container.ItemsList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SemiModifiableListTest {

    SemiModifiableList<String> semiModList;
    ItemsList<String> unmodList;
    ItemsList<String> modList;
    ItemsList<String> extraList;

    ItemsList<Integer> unmodListIntegers;
    ItemsList<Integer> modListIntegers;
    SemiModifiableList<Integer> semiModListIntegers;

    @Before
    public void before() {
        unmodList = new ItemsList<>();
        unmodList.add("KKK");
        unmodList.add("RRR");
        unmodList.add("TTT");
        unmodList.add("OOO");

        modList = new ItemsList<>();
        modList.add("PPP");
        modList.add("LLL");
        modList.add("AAA");

        extraList = new ItemsList<>();
        extraList.add("XXX");
        extraList.add("ZZZ");

        semiModList = new SemiModifiableList<>(unmodList, modList);

        unmodListIntegers = new ItemsList<>();
        modListIntegers = new ItemsList<>();
        semiModListIntegers = new SemiModifiableList<>(unmodListIntegers, modListIntegers);
    }

    @Test
    public void shouldReturnIndexOfElementOfList(){
        unmodListIntegers.add(1);
        unmodListIntegers.add(2);
        unmodListIntegers.add(3);

        modListIntegers.add(9);
        modListIntegers.add(8);
        modListIntegers.add(7);
        assertEquals(3, semiModListIntegers.indexOf(9));
        assertEquals(1, semiModListIntegers.indexOf(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenElementExistInBothLists(){
        unmodListIntegers.add(9);
        unmodListIntegers.add(8);
        unmodListIntegers.add(2);

        modListIntegers.add(1);
        modListIntegers.add(2);
        modListIntegers.add(3);
        semiModListIntegers.remove(Integer.valueOf(2));
    }

    @Test
    public void shoudIterateBothPartsOfList(){
        unmodListIntegers.add(1);
        unmodListIntegers.add(2);
        unmodListIntegers.add(3);

        modListIntegers.add(9);
        modListIntegers.add(8);
        modListIntegers.add(7);

        Iterator it = semiModListIntegers.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertEquals(8, it.next());
    }
    @Test
    public void shouldReturnSize() {
        Assert.assertEquals(7, semiModList.size());
    }

    @Test
    public void shouldReturnFalseWhenListIsNotEmpty(){
        assertFalse(semiModList.isEmpty());
    }

    @Test
    public void shouldReturnTrueWhenContains() {
        assertTrue(semiModList.contains("LLL"));
    }

    @Test
    public void shouldReturnTrueWhenContainsAll() {
        ItemsList<String> l = new ItemsList<>();
        l.add("PPP");
        l.add("KKK");
        l.add("LLL");

        assertTrue(semiModList.containsAll(l));
    }

    @Test
    public void shouldReturnArrayOfModAndUnmodListsWhenToArray() {
        assertEquals(semiModList.toArray().length, unmodList.size() + modList.size());
    }

    @Test
    public void shouldReturnTrueWhenAddToList() {
        final int unmodSizeBefore = unmodList.size();
        final int modSizebefore = modList.size();

        assertTrue(semiModList.add("GGG"));
        assertEquals(unmodSizeBefore, unmodList.size());
        assertFalse(modSizebefore == modList.size());
    }

    @Test
    public void shouldIncreaseListWhenAddByIndex() {
        final int listSizebefore = semiModList.size();
        semiModList.add(5, "HHH");

        assertTrue(listSizebefore != semiModList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddByIndexLessThanUnmodListSize() {
        semiModList.add(2, "III");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenAddByIndexMoreThanListSize() {
        semiModList.add(semiModList.size() + 1, "III");
    }


    @Test
    public void shouldReturnTrueWhenAddAllToList() {
        final int unmodSizeBefore = unmodList.size();
        final int modSizebefore = modList.size();

        assertTrue(semiModList.addAll(extraList));

        assertEquals(unmodSizeBefore, unmodList.size());
        assertFalse(modSizebefore == modList.size());
    }

    @Test
    public void shouldIncreaseListWhenAddAllByIndex() {
        final int listSizebefore = semiModList.size();
        semiModList.addAll(5, extraList);

        assertTrue(listSizebefore != semiModList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddAllByIndexLessThanUnmodListSize() {
        semiModList.addAll(2, extraList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenAddAllByIndexMoreThanListSize() {
        semiModList.addAll(semiModList.size() + 1, extraList);
    }

    @Test
    public void shouldReturnTrueWhenRemoveFromList() {
        final int unmodSizeBefore = unmodList.size();
        final int modSizebefore = modList.size();

        assertTrue(semiModList.remove("LLL"));

        assertEquals(unmodSizeBefore, unmodList.size());
        assertFalse(modSizebefore == modList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRemoveElementOfUnmodList() {
        semiModList.remove("RRR");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRemoveByIndexOfUnmodList() {
        semiModList.remove(3);
    }

    @Test
    public void shouldDecreaseListWhenRemoveByIndexMoreThanUnmodListSize() {
        final int unmodSizeBefore = unmodList.size();
        final int modSizebefore = modList.size();

        semiModList.remove(5);

        assertEquals(unmodSizeBefore, unmodList.size());
        assertFalse(modSizebefore == modList.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRemoveAllIncludingUnmodListElements() {
        extraList.add("RRR");
        semiModList.removeAll(extraList);
    }

    @Test
    public void shouldReturnTrueWhenRemoveAll() {
        extraList.add("LLL");
        assertTrue(semiModList.removeAll(extraList));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRetainAllNotIncludingUnmodListElements() {
        extraList.add("RRR");

        semiModList.retainAll(extraList);
    }

    @Test
    public void shouldReturnTrueWhenRetainAll() {
        extraList.add("LLL");
        extraList.add("KKK");
        extraList.add("RRR");
        extraList.add("TTT");
        extraList.add("OOO");

        assertTrue(semiModList.retainAll(extraList));
    }

    @Test
    public void shouldReturnElementWhenIndexLessThanUnmodListSize(){
        assertEquals("KKK" , semiModList.get(0));
    }

    @Test
    public void shouldReturnElementWhenIndexMoreThanUnmodListSize(){
        assertEquals("LLL" , semiModList.get(5));
    }

    @Test(expected =IllegalArgumentException.class)
    public void shouldThrowExceptionIfIndexLessThanUnmodListSize(){
        semiModList.set(1, "NNN");
    }


    @Test
    public void shouldReturnOldSettedElementIfIndexMoreThanUnmodListSize(){
        assertEquals("LLL", semiModList.set(5, "NNN"));
    }

    @Test
    public void shouldReturnIndexOfUnmodListElement(){
        assertEquals(0 , semiModList.indexOf("KKK"));
    }

    @Test
    public void shouldReturnIndexOfModListElement(){
        assertEquals(5 , semiModList.indexOf("LLL"));
    }

    @Test
    public void shouldIterateModAndUnmodList(){
        ItemsList itl = new ItemsList();
        Iterator it = semiModList.iterator();

        while(it.hasNext()){
            itl.add(it.next());
        }

       assertTrue(semiModList.containsAll(itl));
    }
}