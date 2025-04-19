package com.timata.downloader.parseInput;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class FilesURLSParser {
    private static final String URL_FILE = System.getProperty("user.home") + "/Desktop/ParallelFileDownloader/config/urls.txt";
    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?|ftp)://[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+.*"
    );

    private FilesURLSParser() {
    }

    /**
     * Reads and validates URLs from a file.
     *
     * @return Map of valid, unique URLs
     */
    public static Map<String, Boolean> filesURLSParser() throws IOException {
        Map<String, Boolean> URLmap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(URL_FILE))) {
            String url = null;
            boolean isValid = false;
            while ((url = reader.readLine()) != null) {
                url = url.trim();
                if (url.isEmpty())
                    continue;
                isValid = URL_PATTERN.matcher(url).matches();
                URLmap.putIfAbsent(url, isValid);
            }
        }

        return URLmap;
    }
}