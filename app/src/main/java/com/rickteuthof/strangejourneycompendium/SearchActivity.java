package com.rickteuthof.strangejourneycompendium;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static String searchType;
    public static ArrayList<String> results;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView rv = findViewById(R.id.recyclerView);

        if (searchType.equals("skill")) {
            results = MainActivity.skillNames;
        } else {
            results = MainActivity.demonNames;
        }
        adapter = new RecyclerViewAdapter(this, results);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (searchType.equals("skill")) {
                    SkillActivity.name = results.get(position);
                    Intent obj = new Intent(SearchActivity.this, SkillActivity.class);
                    startActivity(obj);
                } else {
                    DemonActivity.name = results.get(position);
                    Intent obj = new Intent(SearchActivity.this, DemonActivity.class);
                    startActivity(obj);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        SearchView s = findViewById(R.id.searchBar);
        s.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        adapter.filter(query);
        return false;
    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}