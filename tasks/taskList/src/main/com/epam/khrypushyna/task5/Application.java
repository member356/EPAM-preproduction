package com.epam.khrypushyna.task5;

import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.ReaderImpl;
import com.epam.khrypushyna.shop.view.WriterImpl;
import com.epam.khrypushyna.task5.filters.FileExtensionFilter;
import com.epam.khrypushyna.task5.filters.FileLastModifiedFilter;
import com.epam.khrypushyna.task5.filters.FileNameFilter;
import com.epam.khrypushyna.task5.filters.FileSizeFilter;
import com.epam.khrypushyna.shop.view.Writer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
    private Writer writer = new WriterImpl(System.out);
    private Reader reader = new ReaderImpl(System.in);

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        String root;
        writer.write("Do you want to search for a file in current project? (0/1)");
        if (Integer.valueOf(reader.readString()) == 1) {
            root = System.getProperty("user.dir");
        } else {
            writer.write("Enter a root directory");
            root = reader.readString();
        }

        writer.write(System.lineSeparator() + "WRAPPER RUN:");
        writer.write("Enter File name with extension: ");
        String fileName = reader.readString();

        FileWrapper file = new FileWrapper(root + "/" + fileName);
        for (String string : file) {
            System.out.println(string);
        }

        writer.write(System.lineSeparator() + "FILTERS RUN:");
        FilterManager filter = new FilterManager();

        askForNameFilter(reader, filter);
        askForExtensionFilter(reader, filter);
        askForSizeFilter(reader, filter);
        askForDateFilter(reader, filter);

        FileSearcher searcher = new FileSearcher();
        for (File temp : searcher.getFilteredFileList(filter.build(), root)) {
            writer.write("Your file: " + temp.getAbsolutePath());
        }
    }

    private void askForNameFilter(Reader scanner, FilterManager filter) {
        writer.write("Do you want to search by File name? (0/1)");
        if (Integer.valueOf(scanner.readString()) == 1) {
            writer.write("Enter a File name");
            filter.addFilter(new FileNameFilter(scanner.readString()));
        }
    }

    private void askForExtensionFilter(Reader scanner, FilterManager filter) {
        writer.write("Do you want to search by File extension? (0/1)");
        if (Integer.valueOf(scanner.readString()) == 1) {
            writer.write("Enter extension");
            filter.addFilter(new FileExtensionFilter(scanner.readString()));
        }
    }

    private void askForSizeFilter(Reader scanner, FilterManager filter) {
        writer.write("Do you want to search by File size? (0/1)");
        if (Integer.valueOf(scanner.readString()) == 1) {
            writer.write("Enter size 'from' in bytes");
            long from = Long.valueOf(scanner.readString());
            writer.write("Enter size 'to' in bytes");
            long to = Long.valueOf(scanner.readString());
            filter.addFilter(new FileSizeFilter(from, to));
        }
    }

    private void askForDateFilter(Reader scanner, FilterManager filter) {
        writer.write("Do you want to search by File modification date? (0/1)");
        if (Integer.valueOf(scanner.readString()) == 1) {
            try {
                writer.write("Enter date 'from' in format yyyy.MM.dd");
                String fromString = scanner.readString();
                Date from = df.parse(fromString);

                writer.write("Enter date 'to' in format yyyy.MM.dd");
                String toString = scanner.readString();
                Date to = df.parse(toString);

                filter.addFilter(new FileLastModifiedFilter(from, to));
            } catch (ParseException e) {
                System.err.println("Exception during parsing date");
            }

        }
    }
}
