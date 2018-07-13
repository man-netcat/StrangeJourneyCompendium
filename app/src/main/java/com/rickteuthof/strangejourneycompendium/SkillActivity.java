package com.rickteuthof.strangejourneycompendium;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.CompoundButton;
        import android.widget.Switch;
        import android.widget.TextView;

        import java.util.ArrayList;

public class SkillActivity extends AppCompatActivity {
    public static String name;
    public final Skill[] skills = MainActivity.skills;
    public static boolean skillsChecked = true;
    public static boolean sourceChecked = true;
    private SkillAdapter adapter;
    public static ArrayList<String> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        Skill skill = getCurrentSkill(name);
        handleTextViews(skill);
        handleRecyclerView();
        handleSwitches();
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

        skillNameView.setText(String.format("Name: %s", skill.getName()));

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
    }

    public void handleRecyclerView() {
        results = new ArrayList<>();
        results.addAll(MainActivity.demonNames);
        RecyclerView rv = findViewById(R.id.skill_demons);
        adapter = new SkillAdapter(this, results);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.addOnItemTouchListener(new SearchActivity.RecyclerTouchListener(getApplicationContext(), rv, new SearchActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DemonActivity.name = results.get(position);
                Intent obj = new Intent(SkillActivity.this, DemonActivity.class);
                startActivity(obj);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        adapter.filter(name);
    }

    public void handleSwitches() {
        Switch skillSwitch = findViewById(R.id.skill_switch);
        Switch sourceSwitch = findViewById(R.id.source_switch);
        skillSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                skillsChecked = isChecked;
                adapter.filter(name);
            }
        });

        sourceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sourceChecked = isChecked;
                adapter.filter(name);
            }
        });
    }
}
