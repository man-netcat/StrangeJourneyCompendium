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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private static ArrayList<String> results;
    private ArrayList<String> itemList;

    SearchAdapter(Context ctx, ArrayList<String> results) {

        inflater = LayoutInflater.from(ctx);
        SearchAdapter.results = results;
        this.itemList = new ArrayList<>();
        this.itemList.addAll(results);
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        holder.result.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView result;

        MyViewHolder(View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.result);
        }
    }

    public void filter(String query) {
        query = query.toLowerCase(Locale.getDefault());
        SearchActivity.results.clear();
        if (query.length() == 0) {
            SearchActivity.results.addAll(itemList);
        } else {
            for (String item : itemList) {
                if (item.toLowerCase(Locale.getDefault()).contains(query)) {
                    SearchActivity.results.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}