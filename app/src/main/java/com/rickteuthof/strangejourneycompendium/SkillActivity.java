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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SkillActivity extends AppCompatActivity {
    public static String name;
    public final Skill[] skills = MainActivity.skills;
    public static boolean skillsChecked = true;
    public static boolean sourceChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        Skill skill = getCurrentSkill(name);
        setTextViews(skill);
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
        TextView nameView = findViewById(R.id.skill_name);
        TextView costView = findViewById(R.id.skill_cost);
        TextView effectView = findViewById(R.id.skill_effect);
        TextView elementView = findViewById(R.id.skill_element);
        TextView rankView = findViewById(R.id.skill_rank);
        TextView accuracyView = findViewById(R.id.skill_accuracy);
        TextView powerView = findViewById(R.id.skill_power);
        TextView inheritView = findViewById(R.id.skill_inherit);
        TextView demonStringView = findViewById(R.id.demon_string);

        nameView.setText(String.format("Name: %s", skill.getName()));

        int cost = skill.getCost();
        if (cost != 0) {
            costView.setText(String.format("Cost: %s", Integer.toString(cost - 1000)));
        } else {
            costView.setText(R.string.noCost);
        }

        effectView.setText(String.format("Effect: %s", skill.getEffect()));
        elementView.setText(String.format("Element: %s", skill.getElement()));

        int rank = skill.getRank();
        if (rank != 0) {
            rankView.setText(String.format("Rank: %s", Integer.toString(rank)));
        } else {
            rankView.setText(R.string.noRank);
        }

        int accuracy = skill.getAccuracy();
        if (accuracy != 0) {
            accuracyView.setText(String.format("Accuracy: %s", Integer.toString(accuracy)));
        } else {
            accuracyView.setText(R.string.noAccuracy);
        }

        int power = skill.getPower();
        if (power != 0) {
            powerView.setText(String.format("Power: %s", Integer.toString(power)));
        } else {
            powerView.setText(R.string.noPower);
        }

        String inherit = skill.getInherit();
        if (inherit != null) {
            inheritView.setText(String.format("Inherit: %s", inherit));
        } else {
            inheritView.setText(R.string.noInherit);
        }

        demonStringView.append(" " + name + ":");

        ArrayList<String> demons = getDemons(name);
        handleRecyclerView(demons);
    }

    public ArrayList<String> getDemons(String skillName) {
        ArrayList<String> results = new ArrayList<>();
        Demon[] demons = MainActivity.demons;

        Switch skills = findViewById(R.id.skill_switch);
        Switch source = findViewById(R.id.source_switch);

        skills.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                skillsChecked = isChecked;
            }
        });

        source.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sourceChecked = isChecked;
            }
        });


        for (Demon demon : demons) {
            if (skillsChecked && demon.getSkills().contains(skillName) ||
                    sourceChecked && demon.getSource().contains(skillName)) {
                results.add(demon.getName());
            }
        }
        Collections.sort(results);
        return results;
    }

    public void handleRecyclerView(final ArrayList<String> demonList) {
        RecyclerView rv = findViewById(R.id.skill_demons);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, demonList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.addOnItemTouchListener(new SearchActivity.RecyclerTouchListener(getApplicationContext(), rv, new SearchActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DemonActivity.name = demonList.get(position);
                Intent obj = new Intent(SkillActivity.this, DemonActivity.class);
                startActivity(obj);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchActivity.ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchActivity.ClickListener clickListener) {
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
