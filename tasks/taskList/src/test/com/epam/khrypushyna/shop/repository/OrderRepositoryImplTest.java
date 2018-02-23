package com.epam.khrypushyna.shop.repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderRepositoryImplTest {

    private OrderRepository orderRepository = new OrderRepositoryImpl();
    private Map<Integer, Integer> cart = new LinkedHashMap<>();

    @Before
    public void before(){
        cart.put(1, 2);
        cart.put(2, 3);
        long dateLong = new Date().getTime() + 50;
        orderRepository.add(new Date(dateLong), cart);
    }

    @Test
    public void shouldIncreaseMapSizeWhenAdd(){
        cart.put(3, 1);
        long dateLong = new Date().getTime() + 60;
        orderRepository.add(new Date(dateLong), cart);

        assertEquals(2, orderRepository.getAll().size());
    }

    @Test
    public void shouldReturnEmptyMapWhenGetAllEmptyMap(){
        assertEquals(1, orderRepository.getAll().size());
    }

}