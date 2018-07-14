package com.rickteuthof.strangejourneycompendium;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private static ArrayList<String> results;

    SourceAdapter(Context ctx, ArrayList<String> results) {
        inflater = LayoutInflater.from(ctx);
        SourceAdapter.results = results;

    }

    @NonNull
    @Override
    public SourceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceAdapter.MyViewHolder holder, int position) {
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
        Demon[] demons = MainActivity.demons;
        SkillActivity.sourceResults.clear();
        for (Demon demon : demons) {
            if (SkillActivity.sourceChecked && demon.getSource().contains(query)) {
                results.add(demon.getName());
            }
        }
        notifyDataSetChanged();
    }
}