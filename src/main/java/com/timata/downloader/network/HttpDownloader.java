package com.timata.downloader.network;

import java.io.*;
import java.net.*;

public class HttpDownloader {
    private static final int BUFFER_SIZE = 8192;
    private static final String DOWNLOADS_DIR = System.getProperty("user.home") + "/Desktop/ParallelFileDownloader/downloads/";

    private HttpDownloader() {}

    private static String getExtension(String url, HttpURLConnection httpConn) {
        String contentType = httpConn.getContentType();
        return getExtensionFromContentType(contentType);
    }

    private static String getExtensionFromContentType(String contentType) {
        return switch (contentType) {
            case "application/pdf" -> ".pdf";
            case "image/png" -> ".png";
            case "text/plain" -> ".txt";
            case "text/html" -> ".html";
            case "application/zip" -> ".zip";
            case "image/jpeg" -> ".jpg";
            default -> ".dat"; // Fallback
        };
    }

    public static void downloadFile(String fileUrl, final int fileNumber) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start download file Number -> " + fileNumber);

        URL url = null;
        HttpURLConnection httpConn = null;
        try {
            url = new URL(fileUrl);
            httpConn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.err.println("Error in " + threadName + ": " + e.getMessage());
            return;
        }
        String fileName = "file_" + fileNumber + getExtension(fileUrl, httpConn);

        try (BufferedInputStream in = new BufferedInputStream(httpConn.getInputStream(), BUFFER_SIZE);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(DOWNLOADS_DIR + fileName), BUFFER_SIZE)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println(threadName + " finish download file Number -> " + fileNumber);
        } catch (IOException e) {
            System.err.println(threadName + " failed to download file Number -> " + fileNumber + ": " + e.getMessage());
            return ;
        } finally {
            httpConn.disconnect();
        }
    }
}
