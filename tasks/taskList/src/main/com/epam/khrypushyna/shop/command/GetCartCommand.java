package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.CartService;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.Map;

public class GetCartCommand implements Command {

    private CartService cartService;
    private Writer writer;

    public GetCartCommand(CartService cartService, Writer writer) {
        this.cartService = cartService;
        this.writer = writer;
    }

    @Override
    public void execute() {
        Map<Integer, Integer> products = cartService.getAll();

        if (!products.isEmpty()) {
            writer.write("CART:\n");
            for(Map.Entry pair : products.entrySet()){
                writer.write("Key = " + pair.getKey() + " - quantity = " + pair.getValue());
            }
        } else {
            writer.write("Cart is empty");
        }
    }

    @Override
    public String commandName() {
        return "Get products in cart";
    }
}
