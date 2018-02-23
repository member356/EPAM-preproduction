package com.epam.khrypushyna.task5.filters;

import java.io.File;
import java.util.Date;

public class FileLastModifiedFilter extends BaseFileFilter {

    private Date from;
    private Date to;

    public FileLastModifiedFilter(Date from, Date to) {
        checkForNullWithExceptions(from, to);
        this.from = from;
        this.to = to;
    }

    @Override
    protected boolean checkFile(File file) {
        long lastMod = file.lastModified();
        return (lastMod >= from.getTime() && lastMod <= to.getTime());
    }

    private void checkForNullWithExceptions(Date from, Date to) {
        if (from == null || to == null) {
            throw new NullPointerException();
        }
    }
}
