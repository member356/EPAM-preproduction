package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.repository.CartRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartServiceImplTest {

    private CartRepository cartRepositoryMock = mock(CartRepository.class);
    private CartCacheService cartCacheServiceMock = mock(CartCacheService.class);
    private CartService cartService = new CartServiceImpl(cartRepositoryMock, cartCacheServiceMock);
    private Map<Integer, Integer> cartMap = new LinkedHashMap<>();

    @Before
    public void before(){
        cartMap.put(1, 3);
        cartMap.put(2, 1);

        when(cartRepositoryMock.getAll()).thenReturn(cartMap);
    }

    @Test
    public void shouldReturncartMapWhenGetAll(){
        int catalogSize = cartService.getAll().size();
        verify(cartRepositoryMock).getAll();
        assertEquals(2, catalogSize);
    }

    @Test
    public void shouldCallRepositoryAddWhenAddWithService(){
        cartService.add(1);
        verify(cartRepositoryMock).add(1);
    }

    @Test
    public void shouldReturnEmptyMapWhenremoveAll(){
        cartService.removeAll();
        verify(cartRepositoryMock).removeAll();
    }

}