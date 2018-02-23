package com.epam.khrypushyna.shop.view;

public interface Writer {

    void write(String s);

    void write(Object o);

    public void close();
}
