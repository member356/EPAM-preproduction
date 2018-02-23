package com.epam.khrypushyna.shop.repository;

import com.epam.khrypushyna.shop.entity.Furniture;

import java.util.Map;

public interface CatalogRepository {

    Map<Integer, Furniture> getAll();

    void add(Furniture item);

    void addAll(Map<Integer, Furniture> catalogMap);

    int getSize();
}
