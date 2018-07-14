package com.rickteuthof.strangejourneycompendium;

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SkillActivity extends AppCompatActivity {
    public static String name;
    public static Skill[] skills = MainActivity.skills;
    public static boolean skillsChecked = true;
    public static boolean sourceChecked = true;
    public static ArrayList<String> skillResults;
    public static ArrayList<String> sourceResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        Skill skill = getCurrentSkill(name);
        handleTextViews(skill);
        handleRecyclerView();
    }

    public Skill getCurrentSkill(String skillName) {
        for (Skill skill : skills) {
            if (skill.getName().equals(skillName)) {
                return skill;
            }
        }
        return null;
    }

    public void handleTextViews(Skill skill) {
        TextView skillNameView = findViewById(R.id.skill_name);
        TextView costView = findViewById(R.id.skill_cost);
        TextView effectView = findViewById(R.id.skill_effect);
        TextView elementView = findViewById(R.id.skill_element);
        TextView rankView = findViewById(R.id.skill_rank);
        TextView accuracyView = findViewById(R.id.skill_accuracy);
        TextView powerView = findViewById(R.id.skill_power);
        TextView inheritView = findViewById(R.id.skill_inherit);
        TextView demonStringView = findViewById(R.id.demon_string);

        skillNameView.setText(skill.getName());

        int cost = skill.getCost();
        if (cost != 0) {
            costView.setText(String.format("%s", Integer.toString(cost - 1000)));
        } else {
            costView.setText(R.string.none);
        }

        effectView.setText(String.format("%s", skill.getEffect()));
        elementView.setText(String.format("%s", skill.getElement()));

        int rank = skill.getRank();
        if (rank != 0) {
            rankView.setText(String.format("%s", Integer.toString(rank)));
        } else {
            rankView.setText(R.string.none);
        }

        int accuracy = skill.getAccuracy();
        if (accuracy != 0) {
            accuracyView.setText(String.format("%s", Integer.toString(accuracy)));
        } else {
            accuracyView.setText(R.string.none);
        }

        int power = skill.getPower();
        if (power != 0) {
            powerView.setText(String.format("%s", Integer.toString(power)));
        } else {
            powerView.setText(R.string.none);
        }

        String inherit = skill.getInherit();
        if (inherit != null) {
            inheritView.setText(String.format("%s", inherit));
        } else {
            inheritView.setText(R.string.none);
        }

        demonStringView.append(" " + name + ":");
    }

    public void handleRecyclerView() {
        skillResults = new ArrayList<>();
        sourceResults = new ArrayList<>();

        skillResults.addAll(MainActivity.demonNames);
        sourceResults.addAll(MainActivity.demonNames);

        RecyclerView skillRecyclerView = findViewById(R.id.skill_demons);
        RecyclerView sourceRecyclerView = findViewById(R.id.source_demons);

        SkillAdapter skillAdapter = new SkillAdapter(this, skillResults);
        SourceAdapter sourceAdapter = new SourceAdapter(this, sourceResults);

        skillRecyclerView.setAdapter(skillAdapter);
        sourceRecyclerView.setAdapter(sourceAdapter);

        skillRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        skillRecyclerView.addOnItemTouchListener(new SearchActivity.RecyclerTouchListener(getApplicationContext(), skillRecyclerView, new SearchActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DemonActivity.name = skillResults.get(position);
                Intent obj = new Intent(SkillActivity.this, DemonActivity.class);
                startActivity(obj);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        sourceRecyclerView.addOnItemTouchListener(new SearchActivity.RecyclerTouchListener(getApplicationContext(), sourceRecyclerView, new SearchActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DemonActivity.name = sourceResults.get(position);
                Intent obj = new Intent(SkillActivity.this, DemonActivity.class);
                startActivity(obj);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        skillAdapter.filter(name);
        sourceAdapter.filter(name);
    }
}
