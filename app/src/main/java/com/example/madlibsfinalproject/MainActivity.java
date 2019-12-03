package com.example.madlibsfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent inputScreen = new Intent(this, InputActivity.class);
        Button scenario1 = findViewById(R.id.scenario1);
        Button scenario2 = findViewById(R.id.scenario2);
        Button scenario3 = findViewById(R.id.scenario3);
        scenario1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(inputScreen);
           }
        });
        scenario2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inputScreen);
            }
        });
        scenario3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inputScreen);
            }
        });
    }
}
