package com.rickteuthof.strangejourneycompendium;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private List<String> results;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView resultTextView;

        public MyViewHolder(View view) {
            super(view);
            resultTextView = (TextView) view.findViewById(R.id.result);
        }
    }


    public ResultAdapter(ArrayList<String> results) {
        this.results = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_search, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String result = results.get(position);
        holder.resultTextView.setText(result);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}