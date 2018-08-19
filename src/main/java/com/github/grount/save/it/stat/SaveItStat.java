package com.github.grount.save.it.stat;

import java.util.Map;

public class SaveItStat {
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
        else {
            Map<String, String> arguments = ArgumentsConverter.convert(args);
            Kind kind = TypeFactory.createType(arguments.get("kind"), arguments.get("title"), arguments.get("content"));
            JsonGenerator.generate(kind);
            CommunicationSocket communicationSocket = new CommunicationSocket();
            communicationSocket.initialize();
        }
    }
}
