package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.service.CartService;
import com.epam.khrypushyna.shop.service.CatalogService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

public class AddProductToCartCommand implements Command {

    private CartService cartService;
    private CatalogService catalogService;
    private Reader reader;
    private Writer writer;

    public AddProductToCartCommand(CartService cartService, CatalogService catalogService,
                                   Reader reader, Writer writer) {
        this.cartService = cartService;
        this.catalogService = catalogService;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void execute() {
        writer.write("Enter product id");
        Furniture item = catalogService.getById(reader.readInt());
        cartService.add(item.getId());
        writer.write("Item " + item + " added");
    }

    @Override
    public String commandName() {
        return "Add product to cart";
    }
}
