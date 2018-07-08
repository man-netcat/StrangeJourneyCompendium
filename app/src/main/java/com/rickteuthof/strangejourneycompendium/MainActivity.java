package com.rickteuthof.strangejourneycompendium;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BufferedReader bufferedReader = null;
        InputStream is = getResources().openRawResource(R.raw.demon_data);
        Reader r = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(r);
        Gson gson = new Gson();
        Demon[] demons = gson.fromJson(br, Demon[].class);

        TextView t=new TextView(this);

        t=(TextView)findViewById(R.id.test);
        t.setText("Demon 0 name is: " + demons[0].getName());
    }
}
