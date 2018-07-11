package com.rickteuthof.strangejourneycompendium;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SkillActivity extends AppCompatActivity {
    public static String name;
    public Skill[] skills = MainActivity.skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        Skill skill = getCurrentSkill(name);
    }

    public Skill getCurrentSkill(String name) {
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                return skill;
            }
        }
        return null;
    }

    public void setTextViews(Skill skill) {

    }
}
