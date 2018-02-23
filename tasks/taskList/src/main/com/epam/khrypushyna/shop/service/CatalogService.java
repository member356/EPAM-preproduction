package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.entity.Furniture;

import java.util.Map;

public interface CatalogService {

    Furniture getById(int id);

    Integer getPrice(Integer id);

    Map<Integer, Furniture> getAll();

    void add(Furniture item);

    void addAll(Map<Integer, Furniture> catalogMap);

    int getSize();
}
