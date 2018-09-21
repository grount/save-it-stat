package com.github.grount.save.it.stat;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SetupEnvironmentTest {

    @Test
    void perform_createsFolder_ifNotExist() {
        Path installationFolder = SetupEnvironment.getInstallationPath();
        File file = installationFolder.toFile();

        if (file.exists())
            file.delete();

        SetupEnvironment setupEnvironment = new SetupEnvironment();
        setupEnvironment.perform();

        assertTrue(file.exists());
    }
}