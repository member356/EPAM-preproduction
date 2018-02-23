package com.epam.khrypushyna.task5.filters;

import java.io.File;

public abstract class BaseFileFilter{

    private BaseFileFilter next;

    public void setNext(BaseFileFilter next) {
        this.next = next;
    }

    public boolean doFilter(File file) {
        boolean result = checkFile(file);

        if (result && next != null) {
            return next.doFilter(file);
        }
        return result;
    }

    protected abstract boolean checkFile(File file);
}
