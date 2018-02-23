package com.epam.khrypushyna.task5;

import com.epam.khrypushyna.task5.filters.BaseFileFilter;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {

    private List<BaseFileFilter> filterList;

    public FilterManager() {
        filterList = new ArrayList<>();
    }

    public void addFilter(BaseFileFilter newFilter) {
        filterList.add(newFilter);
    }

    public BaseFileFilter build() {
        BaseFileFilter filter = null;
        BaseFileFilter firstFilter = null;

        for (BaseFileFilter f : filterList) {
            if (firstFilter == null) {
                firstFilter = f;
            } else {
                filter.setNext(f);
            }
            filter = f;
        }
        return firstFilter;
    }
}
