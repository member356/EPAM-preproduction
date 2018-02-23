package com.epam.khrypushyna.shop;

import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageManager {

    private static final String BUNDLE_NAME = "resources";
    private Reader reader;
    private Writer writer;
    private Map<Integer, Locale> localeMap = new HashMap<>();

    public LanguageManager(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;

        localeMap.put(1, new Locale("ru"));
        localeMap.put(2, new Locale("en"));
    }

    public ResourceBundle execute(){
        writer.write("Choose the language:");
        writer.write("1 - ru" + System.lineSeparator() + "2 - en");

        Locale locale = localeMap.get(reader.readInt());
        return ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }
}
