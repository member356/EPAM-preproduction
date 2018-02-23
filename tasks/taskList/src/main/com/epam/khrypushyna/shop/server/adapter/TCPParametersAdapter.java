package com.epam.khrypushyna.shop.server.adapter;

import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TCPParametersAdapter extends Adapter {

    private Map<String, ServerCommand> commands = new HashMap<>();
    private CatalogService catalogService;
    private static final String COUNT_KEY = "getcount";
    private static final String ITEM_KEY = "getitem";

    private static final String PARAMETERS_REGEX = "(getcount|getitem)(=)?(\\d+)?";
    private static final Pattern PARAMETERS_PATTERN = Pattern.compile(PARAMETERS_REGEX);

    public TCPParametersAdapter(CatalogService catalogService) {
        this.catalogService = catalogService;
        commands.put(COUNT_KEY, param -> getCount());
        commands.put(ITEM_KEY, this::getItem);
    }

    @Override
    public String readParameters(String parametersString) {
        return execute(parametersString, commands, PARAMETERS_PATTERN);
    }

    private String getCount() {
        return String.valueOf(catalogService.getSize());
    }

    private String getItem(String param) {
        Furniture item = catalogService.getById(Integer.parseInt(param));
        if (item != null) {
            return item.getClass().getSimpleName() + " id = " + item.getId() + " | price = " + item.getPrice();
        }
        throw new IllegalArgumentException();
    }

}


