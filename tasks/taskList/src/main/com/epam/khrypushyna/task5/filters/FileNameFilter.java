package com.epam.khrypushyna.task5.filters;

import java.io.File;

public class FileNameFilter extends BaseFileFilter {

    private String fileName;

    public FileNameFilter(String fileName) {
        if (fileName == null)
            throw new NullPointerException();
        this.fileName = fileName;
    }

    @Override
    protected boolean checkFile(File file) {
        return file.getName().contains(fileName);
    }
}
