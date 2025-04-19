package com.timata.downloader;

import static com.timata.downloader.core.FileDownloader.parallelDownloading;
import static com.timata.downloader.parseInput.FilesURLSParser.filesURLSParser;

import java.io.*;

public class Main {
    private static final int DEFAULT_THREAD_COUNT = 3;

    private static int checkArgs(String[] args) {
        int threadCount = DEFAULT_THREAD_COUNT;
        if (args.length > 1) {
            throw new IllegalArgumentException("Too many arguments");
        }
        for (String arg : args) {
            if (arg.startsWith("--threadsCount=")) {
                try {
                    threadCount = Integer.parseInt(arg.substring("--threadsCount=".length()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid thread count: " + arg);
                    System.out.println("Using default thread count: " + DEFAULT_THREAD_COUNT);
                }
            } else {
                throw new IllegalArgumentException("Invalid argument: " + arg);
            }
        }
        return threadCount;
    }

    public static void main(String[] args) {
        int threadCount = checkArgs(args);
        try {
            parallelDownloading(filesURLSParser(), threadCount);
        } catch (IOException e) {
            System.err.println("Failed to open the file -> " + e.getMessage());
            System.exit(1);
        }
    }
}
