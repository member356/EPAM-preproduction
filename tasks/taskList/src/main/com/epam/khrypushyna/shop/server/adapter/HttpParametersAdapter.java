package com.epam.khrypushyna.shop.server.adapter;

import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.service.CatalogService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParametersAdapter extends Adapter {

    private Map<String, ServerCommand> commands = new HashMap<>();
    private JSONObject result = new JSONObject();
    private CatalogService catalogService;
    private static final String COUNT_KEY = "count";
    private static final String ITEM_KEY = "item";

    private static final String PARAMETERS_REGEX = "(count|item)(\\?get_info=)?(\\d+)?";
    private static final Pattern PARAMETERS_PATTERN = Pattern.compile(PARAMETERS_REGEX);

    public HttpParametersAdapter(CatalogService catalogService) {
        this.catalogService = catalogService;
        commands.put(COUNT_KEY, param -> getCount());
        commands.put(ITEM_KEY, this::getItem);
    }

    @Override
    public String readParameters(String parametersString) {
        return execute(parametersString, commands, PARAMETERS_PATTERN);
    }

    private String getCount() {
        try {
            result.put("count", String.valueOf(catalogService.getSize()));
        } catch (JSONException e) {
            System.err.println("Exception while using JSON object");
        }
        return result.toString();
    }

    private String getItem(String param) {
        try {
            Furniture item = catalogService.getById(Integer.parseInt(param));
            if (item != null) {
                result.put("name", item.getClass().getSimpleName());
                result.put("price", +item.getPrice());
                return result.toString();
            }
        } catch (JSONException e) {
            System.err.println("Exception while using JSON");
        }
        throw new IllegalArgumentException();
    }

}
