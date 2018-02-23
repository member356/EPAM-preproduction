package com.epam.khrypushyna.shop.repository;

import java.util.Map;

public interface CartRepository {

    Map<Integer, Integer> getAll();

    void add(int itemId);

    void removeAll();
}
