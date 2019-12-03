package com.example.madlibsfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputActivity  extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button generateButton = findViewById(R.id.generateButton);
        final Intent intent = new Intent(this, OutputActivity.class);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.output_screen);
                startActivity(intent);
            }
        });
    }
}
