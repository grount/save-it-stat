package com.github.grount.save.it.stat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FileBase<T> {
    private final LogManager logManager = LogManager.getLogManager();
    private final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Supplier<T> fileType;
    private Path path;

    FileBase(String path, Supplier<T> fileType) {
        this.path = Paths.get(path);
        this.fileType = fileType;
    }

    public Logger getLogger() {
        return logger;
    }

    public Path getPath() {
        return path;
    }

    public T getFileType() {
        return fileType.get();
    }
}
