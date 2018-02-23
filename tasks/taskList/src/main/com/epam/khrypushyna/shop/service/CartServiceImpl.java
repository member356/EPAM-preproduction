package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.repository.CartRepository;

import java.util.Map;

public class CartServiceImpl implements CartService {

    private CartRepository repository;
    private CartCacheService cacheService;

    public CartServiceImpl(CartRepository repository, CartCacheService cacheService) {
        this.repository = repository;
        this.cacheService = cacheService;
    }

    @Override
    public Map<Integer, Integer> getAll() {
        return repository.getAll();
    }

    @Override
    public void add(int itemId) {
        repository.add(itemId);

        int amount = repository.getAll().get(itemId);
        cacheService.add(itemId, amount);
    }

    @Override
    public void removeAll() {
        repository.removeAll();
    }
}
