package com.rickteuthof.strangejourneycompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        Demon[] demons = gson.fromJson(br, Demon[].class);

        TextView t = findViewById(R.id.test);

        for (Demon demon : demons) {
            if (demon.getName().equals("Anahita")) {
                String a = demon.getAilments();
                ArrayList<String> parsed = Parsers.parseAilments(a);
                StringBuilder res = new StringBuilder();
                res.append(a).append("\n\n");
                for (String s : parsed) {
                    res.append(s).append("\n\n");
                }

                t.setText(res.toString());
            }
        }

//        t.setText(String.format("Demon 0 name is: %s", demons[0].getName()));
    }
}
