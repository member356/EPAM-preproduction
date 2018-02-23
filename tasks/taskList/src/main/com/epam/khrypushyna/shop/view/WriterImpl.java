package com.epam.khrypushyna.shop.view;

import java.io.PrintStream;

public class WriterImpl implements Writer {

    private PrintStream output;

    public WriterImpl(final PrintStream output) {
        this.output = output;
    }

    @Override
    public void write(String s){
        output.println(s);
    }

    @Override
    public void write(Object o){
        output.println(o.toString());
    }

    @Override
    public void close(){
        output.close();
    }
}
