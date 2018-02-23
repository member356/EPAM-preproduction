package com.epam.khrypushyna.shop.repository;

import com.epam.khrypushyna.shop.entity.Furniture;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CatalogRepositoryImpl implements CatalogRepository, Serializable {

    private Map<Integer, Furniture> catalog;

    public CatalogRepositoryImpl() {
        this.catalog = new HashMap<>();
    }

    @Override
    public Map<Integer, Furniture> getAll() {
        return Collections.unmodifiableMap(catalog);
    }

    @Override
    public void add(Furniture item) {
        int id = catalog.size() + 1;

        item.setId(id);
        catalog.put(id, item);
    }

    @Override
    public void addAll(Map<Integer, Furniture> catalogMap) {
        catalog.putAll(catalogMap);
    }

    @Override
    public int getSize() {
        return catalog.size();
    }
}
