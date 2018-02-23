package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.CatalogManager;
import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.repository.CatalogRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatalogServiceImplTest {

    private CatalogRepository catalogRepositoryMock = mock(CatalogRepository.class);
    private CatalogManager catalogManagerMock = mock(CatalogManager.class);

    private CatalogService catalogService = new CatalogServiceImpl(catalogRepositoryMock, catalogManagerMock);
    private Map<Integer, Furniture> catalogMap = new HashMap<>();
    private Desk item;

    @Before
    public void before(){
        item = new Desk(12, true, 120);
        catalogMap.put(1, item);
        when(catalogRepositoryMock.getAll()).thenReturn(catalogMap);
    }

    @Test
    public void shouldReturnCatalogMapWhenGetAll(){
        int catalogSize = catalogService.getAll().size();
        verify(catalogRepositoryMock).getAll();
        assertEquals(1, catalogSize);
    }

    @Test
    public void shouldCallRepositoryAddWhenAddWithService(){
        Furniture item = new Desk();
        catalogService.add(item);
        verify(catalogRepositoryMock).add(item);
    }

    @Test
    public void shouldCallRepositoryAddAllWhenAddAllWithService(){
        catalogService.addAll(catalogMap);
        verify(catalogRepositoryMock).addAll(catalogMap);
    }

    @Test
    public void shoulReturnFurnitureObjectWhenGetById(){
        Furniture returnedItem = catalogService.getById(1);
        verify(catalogRepositoryMock).getAll();
        assertEquals(item, returnedItem);
    }

    @Test
    public void shoulReturnPriceWhenGetPriceBuId(){
        int id = catalogService.getPrice(1);
        verify(catalogRepositoryMock).getAll();
        assertEquals(item.getPrice(), id);
    }

}