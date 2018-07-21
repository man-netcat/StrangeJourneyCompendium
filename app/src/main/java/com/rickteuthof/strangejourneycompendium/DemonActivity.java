package com.rickteuthof.strangejourneycompendium;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DemonActivity extends AppCompatActivity {
    public static String name;
    public final Demon[] demons = MainActivity.demons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demon);

        Demon demon = getCurrentDemon(name);

        handleTextViews(demon);
        handleImageView();
    }

    public Demon getCurrentDemon(String name) {
        for (Demon demon : demons) {
            if (demon.getName().equals(name)) {
                return demon;
            }
        }
        return null;
    }

    public void handleTextViews(Demon demon) {
        // Define TextView objects
        TextView nameView = findViewById(R.id.demon_name);
        TextView raceView = findViewById(R.id.demon_race);
        TextView alignmentView = findViewById(R.id.demon_alignment);
        TextView[] skills = new TextView[3];
        TextView[] source = new TextView[3];
        TextView[] resistance = new TextView[8];
        TextView[] ailment = new TextView[9];
        TextView[] stats = new TextView[7];
        skills[0] = findViewById(R.id.demon_skill_1);
        skills[1] = findViewById(R.id.demon_skill_2);
        skills[2] = findViewById(R.id.demon_skill_3);
        source[0] = findViewById(R.id.demon_source_1);
        source[1] = findViewById(R.id.demon_source_2);
        source[2] = findViewById(R.id.demon_source_3);
        TextView inheritanceView = findViewById(R.id.demon_inherits);
        TextView attackView = findViewById(R.id.demon_attack);
        TextView lvlView = findViewById(R.id.demon_lvl);

        stats[0] = findViewById(R.id.demon_hp);
        stats[1] = findViewById(R.id.demon_mp);
        stats[2] = findViewById(R.id.demon_st);
        stats[3] = findViewById(R.id.demon_ma);
        stats[4] = findViewById(R.id.demon_vi);
        stats[5] = findViewById(R.id.demon_ag);
        stats[6] = findViewById(R.id.demon_lu);

        resistance[0] = findViewById(R.id.resist_phys);
        resistance[1] = findViewById(R.id.resist_gun);
        resistance[2] = findViewById(R.id.resist_fire);
        resistance[3] = findViewById(R.id.resist_ice);
        resistance[4] = findViewById(R.id.resist_elec);
        resistance[5] = findViewById(R.id.resist_wind);
        resistance[6] = findViewById(R.id.resist_expel);
        resistance[7] = findViewById(R.id.resist_curse);

        ailment[0] = findViewById(R.id.resist_poison);
        ailment[1] = findViewById(R.id.resist_paralyze);
        ailment[2] = findViewById(R.id.resist_stone);
        ailment[3] = findViewById(R.id.resist_strain);
        ailment[4] = findViewById(R.id.resist_sleep);
        ailment[5] = findViewById(R.id.resist_charm);
        ailment[6] = findViewById(R.id.resist_mute);
        ailment[7] = findViewById(R.id.resist_fear);
        ailment[8] = findViewById(R.id.resist_bomb);

        nameView.setText(demon.getName());
        raceView.setText(demon.getRace());
        lvlView.setText(String.format("%s", Integer.toString((int)demon.getLvl())));

        ArrayList<Integer> statArray = demon.getStats();
        ArrayList<String> resistanceArray = Parsers.parseResistance(demon.getResists());
        ArrayList<String> ailmentArray = Parsers.parseAilments(demon.getAilments());

        for (int i = 0; i < 7; i++) {
            stats[i].setText(String.format("%s", Integer.toString(statArray.get(i))));
        }

        for (int i = 0; i < 8; i++) {
            resistance[i].setText(resistanceArray.get(i));
        }

        for (int i = 0; i < 9; i++) {
            ailment[i].setText(ailmentArray.get(i));
        }

        // Handle alignment
        String alignment = demon.getAlign();
        alignmentView.setText(alignment);
        if (alignment.contains("Law")) {
            alignmentView.setTextColor(Color.parseColor("#428ff4"));
        } else if (alignment.contains("Chaos")) {
            alignmentView.setTextColor(Color.parseColor("#e03e3e"));
        }

        // Handle skills
        ArrayList<String> skillSet = demon.getSkills();
        int skillLength = skillSet.size();
        for (int i = 0; i < skillLength; i++) {
            skills[i].setText(skillSet.get(i));
        }

        // Handle sources
        ArrayList<String> sourceSet = demon.getSource();
        int sourceLength = sourceSet.size();
        for (int i = 0; i < sourceLength; i++) {
            source[i].setText(sourceSet.get(i));
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viewText = ((TextView) v).getText().toString();
                if (!viewText.equals("")) {
                    SkillActivity.name = viewText;
                    Intent obj = new Intent(DemonActivity.this, SkillActivity.class);
                    startActivity(obj);
                }
            }
        };

        // Set OnClickListeners for TextViews
        for (int i = 0; i < 3; i++) {
            skills[i].setOnClickListener(listener);
            source[i].setOnClickListener(listener);
        }

        // Handle inheritance
        StringBuilder inheritanceViewString = new StringBuilder();
        ArrayList<String> inherits = Parsers.parseInherits(demon.getInherits());
        for (String type : inherits) {
            inheritanceViewString.append(type).append("\n");
        }
        inheritanceView.setText(inheritanceViewString.toString());

        StringBuilder attackViewString = new StringBuilder();
        Attack demonAttack = demon.getAttack();
        if (demonAttack != null) {
            ArrayList<String> attack = Parsers.parseAttack(demonAttack);
            for (String attribute : attack) {
                attackViewString.append(attribute).append("\n");
            }
            attackView.setText(attackViewString.toString());
        } else {
            attackView.setText(R.string.attributes);
        }
    }

    public void handleImageView() {
        ImageView sprite = findViewById(R.id.placeholder);
        String imageName = name.toLowerCase()
                .replace(' ', '_')
                .replace('-', '_');
        if (imageName.equals("long")) {
            // long is a reserved keyword...k
            imageName = "long_";
        } else if (imageName.equals("kamapua'a")) {
            // ugh why does it need to have the stupid apostrophe
            imageName = "kamapua_a";
        }
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resID != 0) {
            sprite.setImageResource(resID);
        }
    }
}
