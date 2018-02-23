package com.epam.khrypushyna.task8.sequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SequenceManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        new SequenceManager().start();
    }

    public void start() throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FinderThread thread;

        while (true) {
            System.out.println("Enter the file name");
            thread = new FinderThread(new File(reader.readLine()));
            thread.start();
            System.out.println("Working...");
            thread.join();
            System.out.println(thread.getResult());
        }
    }

}
