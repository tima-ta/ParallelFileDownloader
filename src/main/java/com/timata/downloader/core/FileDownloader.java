package com.timata.downloader.core;

import java.util.Map;
import java.util.concurrent.*;
import com.timata.downloader.network.HttpDownloader;

public class FileDownloader {

    private FileDownloader() {}

    public static void parallelDownloading(Map<String, Boolean> URLmap, int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int fileNumber = 1;

        for (Map.Entry<String, Boolean> entry : URLmap.entrySet()) {
            if (!entry.getValue()) {
                System.out.println("Failed to download the file. The URL may be invalid or inaccessible: " + entry.getKey());
                continue;
            }
            final int currentFileNumber = fileNumber++;
            executorService.submit(() -> HttpDownloader.downloadFile(entry.getKey(), currentFileNumber));
        }
        executorService.shutdown();
    }
}
