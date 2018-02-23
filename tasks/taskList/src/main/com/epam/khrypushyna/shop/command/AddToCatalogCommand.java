package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.creator.AddCouch;
import com.epam.khrypushyna.shop.creator.AddDesk;
import com.epam.khrypushyna.shop.creator.AddOfficeTable;
import com.epam.khrypushyna.shop.creator.Creator;
import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.service.CatalogService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.HashMap;
import java.util.Map;

public class AddToCatalogCommand implements Command {

    private CatalogService catalogService;
    private Reader reader;
    private Writer writer;

    private Map<Integer, Creator> products = new HashMap<>();

    public AddToCatalogCommand(CatalogService catalogService, Mode mode, Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        this.catalogService = catalogService;

        products.put(1, new AddCouch(mode));
        products.put(2, new AddDesk(mode));
        products.put(3, new AddOfficeTable(mode));
    }

    @Override
    public void execute() {
        writer.write("Select the Furniture product:");
        writer.write("1 - Couch" + System.lineSeparator() +
                "2 - Desk" + System.lineSeparator() +
                "3 - Office Table");

        Creator creator = products.get(reader.readInt());

        Furniture item = creator.create();
        catalogService.add(item);

        writer.write("New product " + item + " was successfully added");
    }

    @Override
    public String commandName() {
        return "Add to catalog";
    }
}
