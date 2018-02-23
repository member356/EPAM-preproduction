package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.CartCacheService;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.Map;

public class CartCacheCommand implements Command {

    private CartCacheService cacheService;
    private Writer writer;

    public CartCacheCommand(CartCacheService cacheService, Writer writer) {
        this.cacheService = cacheService;
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write("CACHE:");
        Map<Integer, Integer> cache = cacheService.getCartCache();
        for (Map.Entry pair : cache.entrySet()) {
            writer.write("Product id: " + pair.getKey() + " Amount: " + pair.getValue());
        }
    }

    @Override
    public String commandName() {
        return "Get last 5 products in your cart";
    }
}
