package com.rickteuthof.strangejourneycompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

public class SpinnerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // Import demon database from MainActivity
        Demon[] demons = MainActivity.demondata;

        // Create list of demons and sort it by name
        ArrayList<String> demonNames = new ArrayList<String>();
        for (Demon demon : demons) {
            demonNames.add(demon.getName());
        }
        Collections.sort(demonNames);

        // Create Spinner object
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, demonNames);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
