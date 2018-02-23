package com.epam.khrypushyna.shop.repository;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class OrderRepositoryImpl implements OrderRepository {

    private NavigableMap<Date, Map<Integer, Integer>> order;

    public OrderRepositoryImpl() {
        this.order = new TreeMap<>();
    }

    @Override
    public NavigableMap<Date, Map<Integer, Integer>> getAll() {
        return Collections.unmodifiableNavigableMap(order);
    }

    @Override
    public void add(Date date, Map<Integer, Integer> cart) {
        order.put(date, cart);
    }

    @Override
    public NavigableMap<Date, Map<Integer, Integer>> getOrdersBetweenDates(Date dateFrom, Date dateTo) {
        NavigableMap<Date, Map<Integer, Integer>> map = (NavigableMap<Date, Map<Integer, Integer>>) order.subMap(dateFrom, dateTo);
        return Collections.unmodifiableNavigableMap(map);
    }

    @Override
    public Map.Entry<Date, Map<Integer, Integer>> getClosestOrder(Date date) {

        Map.Entry<Date, Map<Integer, Integer>> low = order.floorEntry(date);
        Map.Entry<Date, Map<Integer, Integer>> high = order.ceilingEntry(date);
        Map.Entry<Date, Map<Integer, Integer>> res = null;
        if(date.getTime() - low.getKey().getTime() <  high.getKey().getTime() - date.getTime()){
            res = low;
        }
        else {
            res = high;
        }
        return res;
    }
}
