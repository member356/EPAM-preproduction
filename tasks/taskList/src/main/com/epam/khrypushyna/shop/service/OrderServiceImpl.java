package com.epam.khrypushyna.shop.service;

import com.epam.khrypushyna.shop.repository.OrderRepository;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private CatalogService catalogService;

    public OrderServiceImpl(OrderRepository repository, CatalogService catalogService) {
        this.repository = repository;
        this.catalogService = catalogService;
    }

    @Override
    public NavigableMap<Date, Map<Integer, Integer>> getAll() {
        return repository.getAll();
    }

    @Override
    public void add(Date date, Map<Integer, Integer> cart) {
        repository.add(date, cart);
    }

    @Override
    public int countOrderSum(Map<Integer, Integer> cartMap){
        int sum = 0;
        for (Map.Entry cartPair : cartMap.entrySet()) {
            sum = sum + catalogService.getPrice((Integer) cartPair.getKey()) * (Integer) cartPair.getValue();
        }
        return sum;
    }

    @Override
    public NavigableMap<Date, Map<Integer, Integer>> getOrdersBetweenDates(Date dateFrom, Date dateTo){
        return repository.getOrdersBetweenDates(dateFrom, dateTo);
    }

    @Override
    public Map.Entry<Date, Map<Integer, Integer>> getClosestOrder(Date date) {
        return repository.getClosestOrder(date);
    }
}
