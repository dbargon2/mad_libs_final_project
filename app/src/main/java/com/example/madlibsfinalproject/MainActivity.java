package com.example.madlibsfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int scenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent inputScreen = new Intent(this, InputActivity.class);
        Button scenario1 = findViewById(R.id.playButton);

        scenario1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(inputScreen);
           }
        });
    }
}
