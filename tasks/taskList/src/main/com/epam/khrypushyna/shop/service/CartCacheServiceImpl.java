package com.epam.khrypushyna.shop.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartCacheServiceImpl implements CartCacheService{

    private Map<Integer, Integer> cache;
    private final int MAX_ENTRIES = 5;

    public CartCacheServiceImpl() {

        cache = new LinkedHashMap<Integer, Integer>(MAX_ENTRIES + 1, .75F, false) {

            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    @Override
    public Map<Integer, Integer> getCartCache() {
        return Collections.unmodifiableMap(cache);
    }

    @Override
    public void add(int id, int amount) {
        cache.put(id, amount);
    }

}
