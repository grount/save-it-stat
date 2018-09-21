package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.util.Map;
import java.util.logging.Level;

public class SaveItStat {
    private static final FileBase<JSONObject> fileBase = new FileBase<>(Constants.ELEMENTS_PATH, JSONObject::new);

    public static void main(String[] args) {
        String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.contains("Windows") && isAppInstalled()) {
            execute(args);
        }
    }

    private static boolean isAppInstalled() {
        return true;
    }

    private static void execute(String[] args) {
        if (args.length < 3)
            System.out.println("You need to have --kind, --title, --content in any order.");
        else
            executeIfArgumentsAreValid(args);
    }

    private static void executeIfArgumentsAreValid(String[] args) {
        Map<String, String> arguments = ArgumentsConverter.convert(args);
        if (assertArguments(arguments)) return;

        Kind kind = TypeFactory.createType(arguments.get("kind"), arguments.get("title"), arguments.get("content"));
        JSONObject loadedKinds = JsonHandler.getJsonObject(kind);

        if (loadedKinds.length() > 0) {
            CommunicationSocket communicationSocket = new CommunicationSocket();
            boolean status = communicationSocket.sendData(loadedKinds);
            finalize(kind, status);
        }
    }

    private static void finalize(Kind kind, boolean status) {
        if (status)
            JsonHandler.deleteElementsFile();
        else
            JsonHandler.appendNewKindToFile(kind);
    }


    private static boolean assertArguments(Map<String, String> arguments) {
        if (arguments.isEmpty()) {
            fileBase.getLogger().log(Level.SEVERE, "Failed to convert the given arguments.");
            return true;
        }
        return false;
    }
}
