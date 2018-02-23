package com.epam.khrypushyna.task5.filters;

import java.io.File;

public class FileExtensionFilter extends BaseFileFilter {

    private String extension;

    public FileExtensionFilter(String extension) {
        checkForNullWithExceptions(extension);
        this.extension = extension;
    }

    @Override
    protected boolean checkFile(File file) {

        String ext = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            ext = file.getName().substring(i + 1);
        }
        return extension.compareTo(ext) == 0;
    }

    private void checkForNullWithExceptions(String extension) {
        if (extension == null) {
            throw new NullPointerException();
        }
    }
}