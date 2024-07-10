package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Appointment_centralHopital extends AppCompatActivity {
    Button centrallocation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_central_hopital);

        ///Location button
        centrallocation_btn = findViewById(R.id.centrallocation_btn);
        centrallocation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Appointment_centralHopital.this, Centralhospital_Map.class)});
            }
        });
    }
}