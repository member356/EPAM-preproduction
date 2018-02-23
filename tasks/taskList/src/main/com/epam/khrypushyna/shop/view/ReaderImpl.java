package com.epam.khrypushyna.shop.view;

import java.io.InputStream;
import java.util.Scanner;

public class ReaderImpl implements Reader {

    private Scanner r;

    public ReaderImpl(InputStream input) {
        this.r = new Scanner(input);
    }

    @Override
    public int readInt(){
        return Integer.valueOf(r.nextLine());
    }

    @Override
    public String readString(){
        return r.nextLine();
    }

    @Override
    public boolean hasNextLine(){
        return r.hasNextLine();
    }
}
