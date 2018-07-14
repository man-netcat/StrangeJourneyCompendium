package com.rickteuthof.strangejourneycompendium;

import java.util.ArrayList;

public class Parsers {
    public static ArrayList<String> parseResistance(String resists) {
        String[] elements = {"phys: ", "gun: ", "fire: ", "ice: ", "elec: ", "wind: ", "expel: ",
                "curse: "};
        ArrayList<String> parsed = new ArrayList<>(8);
        for (char cur : resists.toCharArray() ) {
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

    public static ArrayList<String> parseAilments(String ailmentRes) {
        ArrayList<String> parsed = new ArrayList<>(9);
        if (ailmentRes == null) {
            for (int i = 0; i < 9; i++) {
                parsed.add("-");
            }
            return parsed;
        }
        String[] ailments = {"poison: ", "paralyze: ", "stone: ", "strain: ", "sleep: ", "charm: ",
                "mute: ", "fear: ", "bomb: "};
        for (char cur : ailmentRes.toCharArray()) {
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

    public static ArrayList<String> parseAttack(Attack attack) {
        ArrayList<String> parsed = new ArrayList<>();
        String ailment = attack.getAilment();
        String element = attack.getElement();
        String hits = attack.getHits();
        String target = attack.getTarget();
        if (ailment != null) {
            parsed.add("ailment: " + ailment);
        }
        if (element != null) {
            parsed.add("element: " + element);
        }
        if (hits != null) {
            parsed.add("hits: " + hits);
        }
        if (target != null) {
            parsed.add("target: " + target);
        }
        return parsed;
    }
}
