package com.epam.khrypushyna.task8.sequence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FinderThread extends Thread {

    private volatile File file;
    private byte[] bytesArray;
    private int subArrayLength;
    private int firstIndex;

    public FinderThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        work();
    }

    private void work() {
        try {
            bytesArray = Files.readAllBytes(Paths.get(file.getName()));
            int size = bytesArray.length;
            int length;
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (bytesArray[i] == bytesArray[j]) {
                        length = countLength(i, j);
                        if (length > subArrayLength) {
                            subArrayLength = length;
                            firstIndex = i;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Exception while reading bytes from file");
        }
    }

    private int countLength(int position1, int position2) {
        int len = 0;
        for (int i = position2; i < bytesArray.length; i++) {
            if (bytesArray[position1 + len] == bytesArray[position2 + len]) {
                len++;
            }
        }
        return len;
    }

    public String getResult() {
        return new String(Arrays.copyOfRange(bytesArray, firstIndex, firstIndex + subArrayLength));
    }

}