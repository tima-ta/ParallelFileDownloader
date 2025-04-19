# ParallelFileDownloader
A Java multi-threaded file downloader using a fixed thread pool for efficient batch downloads.
A high-performance, multi-threaded file downloader implemented in Java that efficiently manages concurrent downloads while optimizing resource utilization.

🌟 Key Features
- Thread Pool Management: Efficiently manages a fixed number of threads to download multiple files without overwhelming system resources
- Smart URL Handling: Validates and processes URLs from an input file
- Automatic File Naming: Intelligently determines file extensions based on content type
- Optimized I/O: Uses buffered streams with 8KB buffer size for efficient downloading
- Error Resilience: Gracefully handles network errors and invalid URLs
- Organized Downloads: Saves files to a dedicated downloads directory on the desktop

## Core Components:
#### Thread Pool Executor (FileDownloader class)

Creates a fixed thread pool to manage concurrent downloads
Efficiently reuses threads to avoid creation/destruction overhead
Implements work stealing - threads automatically pick next available download

#### HTTP Downloader (HttpDownloader class)

Uses HttpURLConnection for robust file downloads
Auto-detects file extensions from Content-Type headers
Implements buffered I/O for optimal performance

#### Input Parser (FilesURLSParser class)

Validates URLs using regex pattern matching
Handles duplicate URLs and empty lines
Reads from a configurable input file

# 🚀 Getting Started

## Prerequisites
Java 17 or higher
Make utility (for build automation)

# Installation & Usage
1. Clone the repo
2. Add your download URLs to ~/Desktop/ParallelFileDownloader/config/urls.txt
3. Build and run:
   for the default number of threads [3 threads]
   ```
   make run
   ```
   To set the number of threads you want
   ```make run ARGS="--threadsCount=5"```


# Makefile Commands

| Command          | Description                                  |
|------------------|----------------------------------------------|
| `make compile`  | Compiles the Java source files              |
| `make run`      | Runs the application (default: 3 threads)   |
| `make clean`    | Cleans the build directory                  |

# 📂 Project Structure
```
src/
└── main/
    └── java/
        └── com/
            └── timata/
                └── downloader/
                    ├── Main.java                    # Entry point
                    ├── core/
                    │   └── FileDownloader.java      # Thread pool management
                    ├── network/
                    │   └── HttpDownloader.java      # HTTP download implementation
                    └── parseInput/
                        └── FilesURLSParser.java     # URL input processing
```


