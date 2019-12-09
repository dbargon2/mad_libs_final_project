package com.example.madlibsfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class OutputActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_screen);
        Button backHome = findViewById(R.id.backHome);
        try {
            setMadLib();
        } catch(JSONException e) {
            System.out.println("Something went wrong in creating the mad lib!");
        }
        final Intent intent = new Intent(this, MainActivity.class);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void setMadLib() throws JSONException {
        TextView title = findViewById(R.id.title);
        TextView output = findViewById(R.id.output);
        output.setText(InputActivity.createMadLib());
        title.setText(InputActivity.getMadLibsTitle());
    }
}
