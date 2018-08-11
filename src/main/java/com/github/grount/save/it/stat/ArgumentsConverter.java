package com.github.grount.save.it.stat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ArgumentsConverter {
    private static final String[] FULL_ARGUMENTS = {"title", "content"};
    private static final String VALID_ARGUMENT_REGEX =
            "^(\\-|\\-\\-)[a-z]*=\".*\""; // The regex is in the form of --".*" or --".*"

    private static Set<String> validArgumentsSet;
    private static Map<String, String> shortArgumentMap;

    static {
        validArgumentsSet = createArgumentsSet();
        shortArgumentMap = createShortArgumentMap();
    }

    private ArgumentsConverter() {
    }

    static Map<String, String> convert(String[] arguments) {
        checkFormValidity(arguments);
        Map<String, String> splittedArguments = splitArguments(arguments);
        checkLeftSideValidity(splittedArguments);
        return convertShortArgumentsToFull(splittedArguments);
    }

    private static Map<String, String> convertShortArgumentsToFull(Map<String, String> arguments) {
        HashMap<String, String> newArgumentMap = new HashMap<>();

        arguments.forEach((key, value) -> {
            if (shortArgumentMap.containsKey(key)) {
                newArgumentMap.put(shortArgumentMap.get(key), value);
            } else {
                newArgumentMap.put(key, value);
            }
        });

        return newArgumentMap;
    }

    private static Map<String, String> createShortArgumentMap() {
        HashMap<String, String> shortArgumentMap = new HashMap<>();

        validArgumentsSet.forEach(argument -> shortArgumentMap.put(getArgumentFirstLetter(argument), argument));
        return shortArgumentMap;
    }

    private static Set<String> createArgumentsSet() {
        Set<String> arguments = new HashSet<>();

        for (String argument : FULL_ARGUMENTS) {
            arguments.add(argument);
            arguments.add(getArgumentFirstLetter(argument));
        }

        return arguments;
    }

    private static String getArgumentFirstLetter(String argument) {
        return Character.toString(argument.charAt(0));
    }

    static void checkFormValidity(String[] arguments) {
        Pattern pattern = Pattern.compile(VALID_ARGUMENT_REGEX);
        for (String argument : arguments) {
            Matcher matcher = pattern.matcher(argument);
            if (!matcher.find())
                throw new IllegalArgumentException("Each argument should start with -- or -");
        }
    }

    private static void checkLeftSideValidity(Map<String, String> arguments) {
        arguments.forEach((key, value) -> {
            if (!validArgumentsSet.contains(key))
                throw new IllegalArgumentException(String.format("The argument: %s is invalid", key));
        });
    }

    private static Map<String, String> splitArguments(String[] arguments) {
        HashMap<String, String> splittedArguments = new HashMap<>();

        for (String argument : arguments) {
            String[] values = argument.split("=", 2);
            String leftValue = trimStartingMinus(values[0]);
            String rightValue = values[1].substring(1, values[1].length() - 1);
            splittedArguments.put(leftValue, rightValue);
        }

        return splittedArguments;
    }

    private static String trimStartingMinus(String value) {
        return value.replaceFirst("^\\-+", "");
    }
}
