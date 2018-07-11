package com.rickteuthof.strangejourneycompendium;

import java.util.ArrayList;

public class Parsers {
    public static ArrayList<String> parseResistance(String resists) {
        String[] elements = {"phys: ", "gun: ", "fire: ", "ice: ", "elec: ", "wind: ", "expel: ",
                "curse: "};
        ArrayList<String> parsed = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            String curElement = elements[i];
            char cur = resists.charAt(i);
            switch (cur) {
                case 's':
                    parsed.add(curElement + "strong");
                    break;
                case 'w':
                    parsed.add(curElement + "weak");
                    break;
                case 'n':
                    parsed.add(curElement + "null");
                    break;
                case 'd':
                    parsed.add(curElement + "drain");
                    break;
                case 'r':
                    parsed.add(curElement + "repel");
                    break;
                default:
                    parsed.add(curElement + "-");
                    break;
            }
        }

        return parsed;
    }

    public static ArrayList<String> parseAilments(String ailmentRes) {
        ArrayList<String> parsed = new ArrayList<>(9);
        if (ailmentRes == null) {
            parsed.add("no special resistances");
            return parsed;
        }
        String[] ailments = {"poison: ", "paralyze: ", "stone: ", "strain: ", "sleep: ", "charm: ",
                "mute: ", "fear: ", "bomb: "};
        for (int i = 0; i < 9; i++) {
            String curAilment = ailments[i];
            char cur = ailmentRes.charAt(i);
            switch (cur) {
                case 's':
                    parsed.add(curAilment + "strong");
                    break;
                case 'w':
                    parsed.add(curAilment + "weak");
                    break;
                case 'n':
                    parsed.add(curAilment + "null");
                    break;
                default:
                    parsed.add(curAilment + "-");
                    break;
            }
        }

        return parsed;
    }

    public static ArrayList<String> parseInherits(String inherits) {
        ArrayList<String> parsed = new ArrayList<>();
        String[] types = {"special", "fire", "ice", "elec", "wind", "expel", "curse", "almighty",
                "phys", "gun", "ailments", "life", "mana", "support", "recover"};
        for (int i = 0; i < 14; i++) {
            if (inherits.charAt(i) == 'o') {
                parsed.add(types[i]);
            }
        }

        if (parsed.size() == 0) {
            parsed.add("none");
        }

        return parsed;
    }
}
