package com.epam.khrypushyna.shop.creator.mode;

import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

public class ConsoleMode implements Mode {

    private Reader reader;
    private Writer writer;

    ConsoleMode(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public String getMaterial(){
        writer.write("Enter material:");
        return reader.readString();
    }

    @Override
    public int getPrice(){
        writer.write("Enter price:");
        return reader.readInt();
    }

    @Override
    public int getHeight(){
        writer.write("Enter height:");
        return reader.readInt();
    }

    @Override
    public boolean getBoolean(){
        writer.write("Cases exist (0/1):");
        return reader.readInt() == 1;
    }
}
