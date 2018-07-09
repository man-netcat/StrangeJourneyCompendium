package com.rickteuthof.strangejourneycompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<String> results;
    private RecyclerView r;
    private ResultAdapter a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Demon[] demons = MainActivity.demons;

        SearchView s = findViewById(R.id.searchbar);
        String query = s.getQuery().toString();
        results = search(query);


        r = findViewById(R.id.results);

        a = new ResultAdapter(results);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        r.setLayoutManager(lm);
        r.setItemAnimator(new DefaultItemAnimator());
        r.setAdapter(a);
    }

    public ArrayList<String> search(String query) {
        Demon[] demons = MainActivity.demons;

        ArrayList<String> results = new ArrayList<>();

        for (Demon demon : demons) {
            String name = demon.getName();
            if (name.contains(query)) {
                results.add(name);
            }
        }

        a.notifyDataSetChanged();

        return results;
    }
}
