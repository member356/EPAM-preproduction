package com.epam.khrypushyna.shop.repository;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public interface OrderRepository {

    void add(Date date, Map<Integer, Integer> cart);

    NavigableMap<Date, Map<Integer, Integer>> getAll();

    NavigableMap<Date, Map<Integer, Integer>> getOrdersBetweenDates(Date dateFrom, Date dateTo);

    Map.Entry<Date, Map<Integer, Integer>> getClosestOrder(Date date);
}
