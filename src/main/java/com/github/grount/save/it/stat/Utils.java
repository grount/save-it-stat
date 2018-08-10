package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static final String WRITING_TO_FILE_ERROR_MESSAGE = "Unable to save %s file: %s";

    private Utils() {
        throw new AssertionError();
    }

    protected static void setFieldOfPropertiesFile(Properties properties, Path path, String key,
                                                   String value, Logger logger) {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            properties.setProperty(key, value);
            properties.store(bw, null);
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    String.format(WRITING_TO_FILE_ERROR_MESSAGE, Constants.FILE_EXISTENCE_NAME, e.getMessage()));
        }
    }

    protected static void setFieldOfPropertiesFile(FileBase fileBase, String key, String value) {
        Utils.setFieldOfPropertiesFile((Properties) fileBase.getFileType(), fileBase.getPath(),
                key, value, fileBase.getLogger());
    }

    protected static void createJsonWithField(FileBase fileBase, String key, String value) {
        try(BufferedWriter bw = Files.newBufferedWriter(fileBase.getPath())) {
            JSONObject parent = (JSONObject) fileBase.getFileType();
            parent.put(key, value);
            bw.write(parent.toString());
        } catch (IOException e) {
            fileBase.getLogger().log(Level.SEVERE,
                    String.format(WRITING_TO_FILE_ERROR_MESSAGE, Constants.FILE_EXISTENCE_NAME, e.getMessage()));
        }
    }

    protected static void setJsonField(FileBase fileBase, String key, String value) {
        try {
            String jsonContent = new String(Files.readAllBytes(fileBase.getPath()));
            JSONObject jsonObject = new JSONObject(jsonContent);
            jsonObject.put(key, value);
            writeGivenJsonToFile(fileBase, jsonObject);
        } catch (IOException e) {
            fileBase.getLogger().log(Level.SEVERE,
                    String.format(WRITING_TO_FILE_ERROR_MESSAGE, Constants.FILE_EXISTENCE_NAME, e.getMessage()));
        }
    }

    private static void writeGivenJsonToFile(FileBase fileBase, JSONObject jsonObject) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(fileBase.getPath())) {
            bw.write(jsonObject.toString());
        }
    }
}
