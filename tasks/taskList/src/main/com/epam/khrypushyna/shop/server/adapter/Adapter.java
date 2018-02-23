package com.epam.khrypushyna.shop.server.adapter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Adapter {

    public abstract String readParameters(String parametersString);

    protected String execute(String param, Map<String, ServerCommand> commands, Pattern pattern){
        Parameters parameters = new Parameters(param, pattern);
        if (!commands.containsKey(parameters.getKey())) {
            throw new IllegalArgumentException();
        }
        return commands.get(parameters.getKey()).execute(parameters.getValue());
    }

    private class Parameters {

        private String key;
        private String value;

        private String getKey() {
            return key;
        }

        private String getValue() {
            return value;
        }

        private Parameters(String request, Pattern pattern) {
            Matcher matcher = pattern.matcher(request);
            if (matcher.find()) {
                key = matcher.group(1);
                if (matcher.groupCount() > 1) {
                    value = matcher.group(3);
                }
            }
        }

    }
}
