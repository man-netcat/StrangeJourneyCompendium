package com.rickteuthof.strangejourneycompendium;

import android.content.Context;

import java.util.ArrayList;

public class SkillAdapter extends Adapter {
    SkillAdapter(Context ctx, ArrayList<String> results) {
        super(ctx, results);
    }

    public void filter(String query) {
        Demon[] demons = MainActivity.demons;
        SkillActivity.skillResults.clear();
        for (Demon demon : demons) {
            if (demon.getSkills().contains(query)) {
                results.add(demon.getName());
            }
        }
        notifyDataSetChanged();
    }
}