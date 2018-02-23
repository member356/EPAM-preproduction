package com.epam.khrypushyna.shop.service;

import java.util.Map;

public interface CartService {

    Map<Integer, Integer> getAll();

    void add(int itemId);

    void removeAll();
}
