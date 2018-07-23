package com.rickteuthof.strangejourneycompendium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SearchAdapter extends Adapter {
    private ArrayList<String> itemList;

    SearchAdapter(Context ctx, ArrayList<String> results) {
        super(ctx, results);
        this.itemList = new ArrayList<>();
        this.itemList.addAll(results);
    }

    public void filter(String query) {
        SearchActivity.results.clear();
        if (query.length() == 0) {
            SearchActivity.results.addAll(itemList);
        } else {
            for (String item : itemList) {
                if (item.toLowerCase(Locale.getDefault())
                        .contains(query.toLowerCase(Locale.getDefault()))) {
                    results.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}