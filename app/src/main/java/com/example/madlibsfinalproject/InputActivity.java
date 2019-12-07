package com.example.madlibsfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InputActivity  extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_screen);

        Button generateButton = findViewById(R.id.generateButton);
        final Intent intent = new Intent(this, OutputActivity.class);
        connect();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void connect() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://madlibz.herokuapp.com/api/random?minlength=5&maxlength=25";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    setUpUI(response);
                } catch(JSONException e) {
                    System.out.println("Something went wrong with the Json!");
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with connecting!");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void setUpUI(JSONObject request) throws JSONException {
        LinearLayout scrollChild = findViewById(R.id.scrollChild);
        JSONArray inputs = request.getJSONArray("blanks");
        for (int i = 0; i < inputs.length(); i++) {
            View inputChunk = getLayoutInflater().inflate(R.layout.chunk_input, scrollChild, false);
            TextView desc = inputChunk.findViewById(R.id.description);
            EditText input = inputChunk.findViewById(R.id.madLibInput);
            desc.setText(inputs.getString(i));
            input.setText("");
            scrollChild.addView(inputChunk);
        }
    }
}
