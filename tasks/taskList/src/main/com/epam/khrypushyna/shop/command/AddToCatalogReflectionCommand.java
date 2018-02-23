package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.creator.Creator;
import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.creator.reflection.AddFurnitureReflection;
import com.epam.khrypushyna.shop.entity.Couch;
import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.entity.OfficeTable;
import com.epam.khrypushyna.shop.service.CatalogService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddToCatalogReflectionCommand implements Command {

    private CatalogService catalogService;
    private Reader reader;
    private Writer writer;
    private Map<Integer, Creator> products = new HashMap<>();
    private ResourceBundle bundle;

    public AddToCatalogReflectionCommand(CatalogService catalogService, Mode mode, Reader reader, Writer writer, ResourceBundle bundle) {
        this.catalogService = catalogService;
        this.reader = reader;
        this.writer = writer;
        this.bundle = bundle;

        products.put(1, new AddFurnitureReflection(mode, Couch.class));
        products.put(2, new AddFurnitureReflection(mode, Desk.class));
        products.put(3, new AddFurnitureReflection(mode, OfficeTable.class));
    }

    @Override
    public void execute() {
        writer.write("REFLECTION MODE");

        writer.write(bundle.getString("message.select"));
        writer.write("1 - " + bundle.getString("product-type.couch") + System.lineSeparator() +
                "2 - " + bundle.getString("product-type.desk") + System.lineSeparator() +
                "3 - " + bundle.getString("product-type.office-table"));

        Creator creator = products.get(reader.readInt());

        Furniture item = creator.create();
        catalogService.add(item);

        writer.write(bundle.getString("message.new-product") + " '" + item + "' " + bundle.getString("message.product-added"));
    }

    @Override
    public String commandName() {
        return "Add to catalog using reflection";
    }
}
