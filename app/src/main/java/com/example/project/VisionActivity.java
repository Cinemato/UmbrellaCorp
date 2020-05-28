package com.example.project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VisionActivity extends AppCompatActivity {

    TextView vision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        vision = (TextView) findViewById(R.id.visionText);

        vision.setText(getIntent().getStringExtra("VISION"));
    }
}
