package com.epam.khrypushyna.shop.repository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartRepositoryImplTest {

    private CartRepository cartRepository = new CartRepositoryImpl();

    @Before
    public void before(){
        cartRepository.add(2);
    }

    @Test
    public void shouldIncreaseMapSizeWhenAdd(){
        cartRepository.add(1);
        assertEquals(2, cartRepository.getAll().size());
    }

    @Test
    public void shouldReturnEmptyMapWhenGetAllEmptyMap(){
        assertEquals(1, cartRepository.getAll().size());
    }

    @Test
    public void shouldReturnEmptyMapWhenRemoveAll(){
        cartRepository.removeAll();
        assertTrue(cartRepository.getAll().isEmpty());
    }
}