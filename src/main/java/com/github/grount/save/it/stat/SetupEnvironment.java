package com.github.grount.save.it.stat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SetupEnvironment {
    private static final Path installationPath;
    private static final File installationFolder;
    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static {
        Path parent = Paths.get(System.getProperty("user.dir")).getParent();
        installationPath = Paths.get(parent.toString(), "Save It Stat");
        installationFolder = installationPath.toFile();
    }

    public static Path getInstallationPath() {
        return installationPath;
    }

    static boolean isInstallationFolderExist() {
        try {
            return installationFolder.exists();
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, String.format("Unable to check directory existence: %s", e.getMessage()));
        }

        return false;
    }

    void perform() {
        createInstallationFolderIfNotExist();
    }

    private void createInstallationFolderIfNotExist() {
        try {
            if (!installationFolder.exists()) {
                installationFolder.mkdir();
            }
        } catch (SecurityException e) {
           logger.log(Level.SEVERE, String.format("Unable to create directory or check existence: %s", e.getMessage()));
        }
    }
}
