package com.github.grount.save.it.stat;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class FileBase<T> {
    private final LogManager logManager = LogManager.getLogManager();
    private final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Supplier<T> fileType;
    private Path path;

    FileBase(@Nonnull String path, @Nonnull Supplier<T> fileType) {
        this.path = Paths.get(path);
        this.fileType = fileType;
    }

    Logger getLogger() {
        return logger;
    }

    Path getPath() {
        return path;
    }

    T getFileType() {
        return fileType.get();
    }
}
