package com.epam.khrypushyna.shop.creator.mode;

import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.util.HashMap;
import java.util.Map;

public class ModeManager {

    private Reader reader;
    private Writer writer;
    private Map<Integer, Mode> mapMode = new HashMap<>();

    public ModeManager(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;

        mapMode.put(1, new AutoMode());
        mapMode.put(2, new ConsoleMode(reader, writer));
    }

    public Mode execute() {
        while(true) {
            writer.write("Select the creator of work:"
                    + System.lineSeparator() + " 1 - Auto input"
                    + System.lineSeparator() + " 2 - Console input");

            return mapMode.get(reader.readInt());
        }
    }
}
