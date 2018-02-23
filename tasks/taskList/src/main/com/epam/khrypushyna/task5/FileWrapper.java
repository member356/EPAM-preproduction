package com.epam.khrypushyna.task5;

import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.ReaderImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class FileWrapper implements Iterable<String> {

    private String filename;

    public FileWrapper(String filename) {
        this.filename = filename;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<String> {

        private Reader reader;

        Iter() {
            try {
                this.reader = new ReaderImpl(new FileInputStream(new File(filename)));
            } catch (FileNotFoundException e) {
                System.err.println("File not found during scanning");
            }
        }

        @Override
        public boolean hasNext() {
            return reader.hasNextLine();
        }

        @Override
        public String next() {
            return reader.readString();
        }
    }
}
