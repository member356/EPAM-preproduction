package com.epam.khrypushyna.shop.repository;

import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatalogRepositoryImplTest {

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();
    private Furniture item = new Desk(120, true, 120);

    @Test
    public void shouldIncreaseMapSizeWhenAdd() {
        catalogRepository.add(item);
        assertEquals(1, catalogRepository.getAll().size());
    }

    @Test
    public void shouldReturnEmptyMapWhenGetAllEmptyMap() {
        assertTrue(catalogRepository.getAll().isEmpty());
    }

}