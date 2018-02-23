package com.epam.khrypushyna.task3;

import com.epam.khrypushyna.task1.container.ItemsList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.function.UnaryOperator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ListLikeSetTest {

    ListLikeSet<String> listLikeSet;
    ItemsList<String> itemsList;

    @Before
    public void before() {
        listLikeSet = new ListLikeSet<>();
        listLikeSet.add("TTT");
        listLikeSet.add("UUU");
        listLikeSet.add("KKK");
        listLikeSet.add("SSS");

        itemsList = new ItemsList<>();
        itemsList.add("HHH");
        itemsList.add("GGG");
    }

    @Test
    public void shouldReturnOldElementWhenSetSameElement(){
        listLikeSet.set(0, "TTT");
    }

    @Test
    public void shouldReturnTrueWhenAddUniqueElement() {
        assertTrue(listLikeSet.add("YYY"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddNotUniqueElement() {
        listLikeSet.add("UUU");
    }

    @Test
    public void shouldReturnTrueWhenAddAllListUniqueElements() {
        assertTrue(listLikeSet.addAll(itemsList));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddllListNotUniqueElements() {
        itemsList.add("UUU");
        itemsList.add("GGG");

        listLikeSet.addAll(itemsList);
    }

    @Test
    public void shouldIncreaseListWhenAddUniqueElementByIndex() {
        listLikeSet.add(1, "VVV");
        assertTrue(listLikeSet.contains("VVV"));
    }

    @Test
    public void shouldReturnTrueWhenAddAllUniqueElementsByIndex() {
        assertTrue(listLikeSet.addAll(1, itemsList));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddNotUniqueElementbyIndex() {
        listLikeSet.add(1, "UUU");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddAllNotUniqueElementByIndex() {
        itemsList.add("UUU");
        listLikeSet.addAll(1, itemsList);
    }

    @Test
    public void shouldReplaceAllWhenReplacedWithNotExistElement(){
        UnaryOperator<String> unaryOperator = new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                String str = s.substring(1, s.length());
                return str;
            }
        };
        listLikeSet.replaceAll(unaryOperator);
        Iterator it = listLikeSet.iterator();
        assertEquals("TT", it.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReplaceAllWhenRaplacedWithExistElement(){
        listLikeSet.replaceAll(new UnaryOperator() {
            @Override
            public Object apply(Object o) {
                return "UUU";
            }
        });
    }

    @Test
    public void shouldReturnOldElementWhenSetNotExistElement(){
        assertEquals("KKK", listLikeSet.set(2, "QQQ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSetExistElement(){
        listLikeSet.set(2, "UUU");
    }
}