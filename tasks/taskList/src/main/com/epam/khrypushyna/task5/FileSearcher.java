package com.epam.khrypushyna.task5;

import com.epam.khrypushyna.task5.filters.BaseFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

    public List<File> search(String root){

        ArrayList<File> files = new ArrayList<>();
        File dir = new File(root);
        for (File file : dir.listFiles()) {
            if (file.isDirectory())
                files.addAll(search(file.getAbsolutePath()));
            else
                files.add(file);
        }
        return files;
    }

    public List<File> getFilteredFileList(BaseFileFilter baseFileFilter, String root) {

        List<File> fileList = search(root);
        List<File> resultList = new ArrayList<>();

        for (File file : fileList) {
            if (baseFileFilter.doFilter(file)) {
                resultList.add(file);
            }
        }
        return resultList;
    }
}
