package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.CatalogManager;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.repository.CatalogRepository;
import java.util.Map;

public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository repository;
    private CatalogManager catalogManager;

    public CatalogServiceImpl(CatalogRepository repository, CatalogManager catalogManager) {
        this.repository = repository;
        this.catalogManager = catalogManager;
    }

    @Override
    public Map<Integer, Furniture> getAll() {
        return repository.getAll();
    }

    @Override
    public void add(Furniture item) {
        repository.add(item);
        catalogManager.save(repository);
    }

    @Override
    public void addAll(Map<Integer, Furniture> catalogMap) {
        repository.addAll(catalogMap);
    }

    @Override
    public int getSize() {
        return repository.getSize();
    }

    @Override
    public Integer getPrice(Integer id) {
        return getById(id).getPrice();
    }

    @Override
    public Furniture getById(int id) {
        Map<Integer, Furniture> catalog = repository.getAll();
        return catalog.get(id);
    }
}
