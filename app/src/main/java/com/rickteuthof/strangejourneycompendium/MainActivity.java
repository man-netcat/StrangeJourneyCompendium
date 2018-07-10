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
    public static ArrayList<String> demonNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create InputStream for JSON file.
        InputStream is = getResources().openRawResource(R.raw.demon_data);
        Reader r = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(r);

        // Create GSON Object from BufferedReader
        Gson gson = new Gson();
        demons = gson.fromJson(br, Demon[].class);

        Demon[] demons = MainActivity.demons;
        demonNames = new ArrayList<>();
        for (Demon demon : demons) {
            demonNames.add(demon.getName());
        }
        Collections.sort(demonNames);

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(obj);
            }
        });
    }
}
