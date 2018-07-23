package com.rickteuthof.strangejourneycompendium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SourceAdapter extends Adapter {
    SourceAdapter(Context ctx, ArrayList<String> results) {
        super(ctx, results);
    }

    public void filter(String query) {
        Demon[] demons = MainActivity.demons;
        SkillActivity.sourceResults.clear();
        for (Demon demon : demons) {
            if (demon.getSource().contains(query)) {
                results.add(demon.getName());
            }
        }
        notifyDataSetChanged();
    }
}