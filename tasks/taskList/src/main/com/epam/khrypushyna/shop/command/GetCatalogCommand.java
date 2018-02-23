package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.service.CatalogService;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.Map;

public class GetCatalogCommand implements Command {

    private CatalogService catalogService;
    private Writer writer;

    public GetCatalogCommand(CatalogService catalogService, Writer writer) {
        this.catalogService = catalogService;
        this.writer = writer;
    }

    @Override
    public void execute() {
        Map<Integer, Furniture> catalog = catalogService.getAll();

        if(!catalog.isEmpty()){
            writer.write("CATALOG:\n");
            for(Map.Entry pair : catalog.entrySet()){
                writer.write("Key:" + pair.getKey() + " - " + pair.getValue());
            }
        }
        else {
            writer.write("Catalog is empty, please add new products to catalog by pressing '9'");
        }
    }

    @Override
    public String commandName() {
        return "Get catalog of products";
    }
}
