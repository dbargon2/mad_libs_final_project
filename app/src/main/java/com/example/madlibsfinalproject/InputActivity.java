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

import java.util.ArrayList;
import java.util.List;

public class InputActivity  extends AppCompatActivity {

    private static JSONArray textVals;
    private static List<EditText> typedInputs = new ArrayList<>();
    private static String title;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_screen);

        Button generateButton = findViewById(R.id.generateButton);
        final Intent intent = new Intent(this, OutputActivity.class);
        connect();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createMadLib();
                    startActivity(intent);
                } catch(JSONException e) {
                    System.out.println("Something went wrong handling JSON generating the Mad Lib!");
                }

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

    public void setUpUI(JSONObject response) throws JSONException {
        textVals = response.getJSONArray("value");
        title = response.getString("title");
        typedInputs = new ArrayList<>();
        LinearLayout scrollChild = findViewById(R.id.scrollChild);
        scrollChild.removeAllViews();
        JSONArray inputs = response.getJSONArray("blanks");

        for (int i = 0; i < inputs.length(); i++) {
            View inputChunk = getLayoutInflater().inflate(R.layout.chunk_input, scrollChild, false);
            TextView desc = inputChunk.findViewById(R.id.description);
            EditText input = inputChunk.findViewById(R.id.madLibInput);
            desc.setText(inputs.getString(i));
            input.setText("");
            typedInputs.add(input);
            scrollChild.addView(inputChunk);
        }
    }

    public static String createMadLib() throws JSONException {
        String result = "";
        for (int i = 0; i < textVals.length(); i++) {
            if (i < typedInputs.size()) {
                result = result + textVals.getString(i) + typedInputs.get(i).getText().toString();
            } else {
                result = result + textVals.getString(i);
            }
        }
        return result;
    }

    public static String getMadLibsTitle() {
        return title;
    }


}
