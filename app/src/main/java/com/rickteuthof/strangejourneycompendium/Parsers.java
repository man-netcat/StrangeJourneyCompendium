package com.rickteuthof.strangejourneycompendium;

import java.util.ArrayList;

public class Parsers {
    public static ArrayList<String> parseResistance(String resists) {
        ArrayList<String> parsed = new ArrayList<>();

        for (char cur : resists.toCharArray()) {
            switch (cur) {
                case 's':
                    parsed.add("strong");
                    break;
                case 'w':
                    parsed.add("weak");
                    break;
                case 'n':
                    parsed.add("null");
                    break;
                case 'd':
                    parsed.add("drain");
                    break;
                case 'r':
                    parsed.add("repel");
                    break;
                default:
                    parsed.add("-");
                    break;
            }
        }

        return parsed;
    }

    public static ArrayList<String> parseAilments(String ailments) {
        ArrayList<String> parsed = new ArrayList<>();

        for (char cur : ailments.toCharArray()) {
            switch (cur) {
                case 's':
                    parsed.add("strong");
                    break;
                case 'w':
                    parsed.add("weak");
                    break;
                case 'n':
                    parsed.add("null");
                    break;
                default:
                    parsed.add("-");
                    break;
            }
        }

        return parsed;
    }
}
