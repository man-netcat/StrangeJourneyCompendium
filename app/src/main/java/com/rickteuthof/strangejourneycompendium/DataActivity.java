package com.rickteuthof.strangejourneycompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataActivity extends AppCompatActivity {
    public static String name;
    public Demon[] demons = MainActivity.demons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Demon demon = getCurrentDemon(name);

        TextView nameView = findViewById(R.id.demon_name);
        TextView raceView = findViewById(R.id.demon_race);
        TextView alignmentView = findViewById(R.id.demon_alignment);
        nameView.setText(name);
        raceView.setText(demon.getRace());
        alignmentView.setText(demon.getAlign());
    }

    public Demon getCurrentDemon(String name) {
        for (Demon demon : demons) {
            if (demon.getName().equals(name)) {
                return demon;
            }
        }
        return null;
    }
}
