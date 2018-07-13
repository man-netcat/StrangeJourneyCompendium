package com.rickteuthof.strangejourneycompendium;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static Demon[] demons;
    public static Skill[] skills;
    public static ArrayList<String> demonNames;
    public static ArrayList<String> skillNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        handleButtons();
    }

    public void initData() {
        // Create InputStreams and BufferedReaders for JSON files.
        InputStream is1 = getResources().openRawResource(R.raw.demon_data);
        InputStream is2 = getResources().openRawResource(R.raw.skill_data);
        Reader r1 = new InputStreamReader(is1);
        Reader r2 = new InputStreamReader(is2);
        BufferedReader br1 = new BufferedReader(r1);
        BufferedReader br2 = new BufferedReader(r2);

        // Create GSON Objects from BufferedReaders
        Gson gson1 = new Gson();
        Gson gson2 = new Gson();
        demons = gson1.fromJson(br1, Demon[].class);
        skills = gson2.fromJson(br2, Skill[].class);

        // Create arrays for demon names and skill names for convenient access.
        demonNames = new ArrayList<>();
        skillNames = new ArrayList<>();
        for (Demon demon : demons) {
            demonNames.add(demon.getName());
        }
        Collections.sort(demonNames);

        for (Skill skill : skills) {
            skillNames.add(skill.getName());
        }
        Collections.sort(skillNames);
    }

    public void handleButtons() {
        Button searchDemon = findViewById(R.id.searchDemon);
        Button searchSkill = findViewById(R.id.searchSkill);
        searchDemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.searchType = "demon";
                Intent obj = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(obj);
            }
        });
        searchSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.searchType = "skill";
                Intent obj = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(obj);
            }
        });
    }
}
