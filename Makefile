# Compiler and flags
JAVAC := javac
JAVA := java
JAVAC_FLAGS := -d ./out
JAVA_FLAGS := -cp ./out

# ANSI color codes
RED    := \033[0;31m
GREEN  := \033[0;32m
YELLOW := \033[0;33m
BLUE   := \033[0;34m
RESET  := \033[0m

# Source files (adjust paths if needed)
SRC_DIR := src/main/java
SOURCES := $(wildcard $(SRC_DIR)/com/timata/downloader/*.java) \
           $(wildcard $(SRC_DIR)/com/timata/downloader/parseInput/*.java) \
           $(wildcard $(SRC_DIR)/com/timata/downloader/core/*.java) \
           $(wildcard $(SRC_DIR)/com/timata/downloader/network/*.java)

# Main class
MAIN_CLASS := com.timata.downloader.Main

# Targets
.PHONY: all compile run clean

all: compile

compile:
	@mkdir -p ./out
	@echo "$(BLUE)🔧 Compiling Java sources...$(RESET)"
	@$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)
	@echo "$(GREEN)✓ Compilation successful!$(RESET)"

run: compile
	@echo "$(BLUE)🚀 Running application...$(RESET)"
	@$(JAVA) $(JAVA_FLAGS) $(MAIN_CLASS) $(ARGS)

clean:
	@echo "$(YELLOW)⚠ Cleaning...$(RESET)"
	@rm -rf ./out
	@echo "$(RED)🗑️ Deleted build files!$(RESET)"
