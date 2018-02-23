package com.epam.khrypushyna.task5.filters;

import java.io.File;

public class FileSizeFilter extends BaseFileFilter {

    private long from;
    private long to;

    public FileSizeFilter(long from, long to) {
        checkRangeWithException(from, to);
        this.from = from;
        this.to = to;
    }

    @Override
    protected boolean checkFile(File file) {
        long fileSize = file.length();
        return (fileSize >= from && fileSize <= to);
    }

    private void checkRangeWithException(long from, long to) {
        if (from < 0 || to < 0 || from > to) {
            throw new IllegalArgumentException();
        }
    }
}
