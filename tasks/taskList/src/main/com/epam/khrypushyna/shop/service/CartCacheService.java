package com.epam.khrypushyna.shop.service;

import java.util.Map;

public interface CartCacheService {

    Map<Integer, Integer> getCartCache();

    void add(int id, int amount);
}
