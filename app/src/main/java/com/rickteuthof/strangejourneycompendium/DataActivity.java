package com.rickteuthof.strangejourneycompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    public static String name;
    public Demon[] demons = MainActivity.demons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Demon demon = getCurrentDemon(name);

        setTextViews(demon);

        ImageView sprite = findViewById(R.id.placeholder);
        int resID = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
        if (resID != 0) {
            sprite.setImageResource(resID);
        }
    }

    public Demon getCurrentDemon(String name) {
        for (Demon demon : demons) {
            if (demon.getName().equals(name)) {
                return demon;
            }
        }
        return null;
    }

    public void setTextViews(Demon demon) {
        // Define TextView objects
        TextView nameView = findViewById(R.id.demon_name);
        TextView raceView = findViewById(R.id.demon_race);
        TextView alignmentView = findViewById(R.id.demon_alignment);
        TextView skill1View = findViewById(R.id.demon_skill_1);
        TextView skill2View = findViewById(R.id.demon_skill_2);
        TextView skill3View = findViewById(R.id.demon_skill_3);
        TextView source1View = findViewById(R.id.demon_source_1);
        TextView source2View = findViewById(R.id.demon_source_2);
        TextView source3View = findViewById(R.id.demon_source_3);
        TextView resistanceView = findViewById(R.id.demon_resistances);
        TextView ailmentView = findViewById(R.id.demon_ailments);

        nameView.setText(demon.getName());
        raceView.setText(demon.getRace());
        alignmentView.setText(demon.getAlign());

        // Handle skills
        ArrayList<String> skillSet = demon.getSkills();
        int skillLength = skillSet.size();
        skill1View.setText(skillSet.get(0));
        if (skillLength > 2) {
            skill2View.setText(skillSet.get(1));
            skill3View.setText(skillSet.get(2));
        } else if (skillLength > 1) {
            skill2View.setText(skillSet.get(1));
            skill3View.setText("");
        } else {
            skill2View.setText("");
            skill3View.setText("");
        }

        // Handle sources
        ArrayList<String> source = demon.getSource();
        int sourceLength = source.size();
        source1View.setText(source.get(0));
        if (sourceLength > 2) {
            source2View.setText(source.get(1));
            source3View.setText(source.get(2));
        } else if (sourceLength > 1) {
            source2View.setText(source.get(1));
            source3View.setText("");
        } else {
            source2View.setText("");
            source3View.setText("");
        }

        // Handle resistances
        StringBuilder resistanceViewString = new StringBuilder();
        ArrayList<String> resists = Parsers.parseResistance(demon.getResists());
        for (String resistance : resists) {
            resistanceViewString.append(resistance).append("\n");
        }
        resistanceView.setText(resistanceViewString.toString());

        // Handle ailments
        StringBuilder ailmentViewString = new StringBuilder();
        ArrayList<String> ailments = Parsers.parseAilments(demon.getAilments());
        for (String ailment : ailments) {
            ailmentViewString.append(ailment).append("\n");
        }
        ailmentView.setText(ailmentViewString.toString());
    }
}
