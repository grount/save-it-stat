package com.github.grount.save.it.stat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestUtils {
    private TestUtils(){}

    protected static void deleteIfFilesExists(Path path) {
        if (path.toFile().exists())
            path.toFile().delete();
    }

    protected static String getFileContent(Path path) {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines != null ? lines.toString() : "";
    }
}
