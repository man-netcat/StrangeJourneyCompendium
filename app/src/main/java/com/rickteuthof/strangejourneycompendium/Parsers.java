package com.rickteuthof.strangejourneycompendium;

import java.util.ArrayList;

public class Parsers {
    public static ArrayList<String> parseResistance(String resists) {
        ArrayList<String> parsed = new ArrayList<>(8);

        for (char cur : resists.toCharArray()) {
            switch (cur) {
                case 's':
                    parsed.add("strong");
                case 'w':
                    parsed.add("weak");
                case 'n':
                    parsed.add("null");
                case 'd':
                    parsed.add("drain");
                case 'r':
                    parsed.add("repel");
                default:
                    parsed.add("-");
            }
        }

        return parsed;
    }

    public static ArrayList<String> parseAilments(String ailments) {
        ArrayList<String> parsed = new ArrayList<>(9);

        for (char cur : ailments.toCharArray()) {
            switch (cur) {
                case 's':
                    parsed.add("strong");
                case 'w':
                    parsed.add("weak");
                case 'n':
                    parsed.add("null");
                default:
                    parsed.add("-");
            }
        }

        return parsed;
    }
}
