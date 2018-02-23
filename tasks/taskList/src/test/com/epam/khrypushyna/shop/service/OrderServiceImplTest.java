package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    private OrderRepository orderRepositoryMock = mock(OrderRepository.class);
    private CatalogService catalogServiceMock = mock(CatalogService.class);

    private OrderService orderService = new OrderServiceImpl(orderRepositoryMock, catalogServiceMock);
    private NavigableMap<Date, Map<Integer, Integer>> orderMap = new TreeMap<>();
    private Map<Integer, Integer> cart = new HashMap<>();

    @Before
    public void before(){
        cart.put(1, 2);
        cart.put(2, 3);
        orderMap.put(new Date(), cart);

        when(orderRepositoryMock.getAll()).thenReturn(orderMap);

        when(catalogServiceMock.getPrice(1)).thenReturn(200);
        when(catalogServiceMock.getPrice(2)).thenReturn(300);
    }

    @Test
    public void shouldReturnOrderMapWhenGetAll(){
        int catalogSize = orderService.getAll().size();
        verify(orderRepositoryMock).getAll();
        assertEquals(1, catalogSize);
    }

    @Test
    public void shouldCallRepositoryAddWhenAddWithService(){
        Date date = new Date();
        orderService.add(date, cart);
        verify(orderRepositoryMock).add(date, cart);
    }


    @Test
    public void shouldReturnSumWhenCountOrderSum(){
        int sum = orderService.countOrderSum(cart);
        verify(catalogServiceMock).getPrice(1);
        assertEquals(1300, sum);
    }

}