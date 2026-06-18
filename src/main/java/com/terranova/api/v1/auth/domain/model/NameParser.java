package com.terranova.api.v1.auth.domain.model;

public class NameParser {

    public static class ParsedName {
        private final String name;
        private final String surname;

        public ParsedName(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
    }

    public static ParsedName splitDynamicName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return new ParsedName("", "");
        }

        String[] tokens = fullName.trim().replaceAll("\\s+", " ").split(" ");

        String name;
        String surname;

        if (tokens.length == 1) {
            name = tokens[0];
            surname = "";
        } else if (tokens.length == 2) {
            name = tokens[0];
            surname = tokens[1];
        } else if (tokens.length == 3) {
            name = tokens[0] + " " + tokens[1];
            surname = tokens[2];
        } else {
            name = tokens[0] + " " + tokens[1];
            StringBuilder surnameBuilder = new StringBuilder();
            for (int i = 2; i < tokens.length; i++) {
                surnameBuilder.append(tokens[i]).append(" ");
            }
            surname = surnameBuilder.toString().trim();
        }

        return new ParsedName(name, surname);
    }
}