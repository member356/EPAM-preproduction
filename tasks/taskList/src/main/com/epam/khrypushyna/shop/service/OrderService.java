package com.epam.khrypushyna.shop.service;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public interface OrderService {

    void add(Date date, Map<Integer, Integer> cart);

    NavigableMap<Date, Map<Integer, Integer>> getAll();

    int countOrderSum(Map<Integer, Integer> cartMap);

    NavigableMap<Date, Map<Integer, Integer>> getOrdersBetweenDates(Date dateFrom, Date dateTo);

    Map.Entry<Date, Map<Integer, Integer>> getClosestOrder(Date date);
}
