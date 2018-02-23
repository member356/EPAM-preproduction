package com.epam.khrypushyna.shop.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartRepositoryImpl implements CartRepository {

    private Map<Integer, Integer> cart;

    public CartRepositoryImpl() {
        this.cart = new LinkedHashMap<>();
    }

    @Override
    public Map<Integer, Integer> getAll() {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public void add(int itemId) {
        if (cart.containsKey(itemId)) {
            Integer value = cart.get(itemId);
            cart.put(itemId, ++value);
        } else {
            cart.put(itemId, 1);
        }
    }

    @Override
    public void removeAll() {
        cart = new LinkedHashMap<>();
    }
}
