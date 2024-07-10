package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Appointment_GenaralHospital extends AppCompatActivity {
    Button genarallocation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_genaral_hospital);

        ///Location button
        genarallocation_btn = findViewById(R.id.genarallocation_btn);
        genarallocation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Appointment_GenaralHospital.this, Genaralhospital_Map.class)});
            }
        });
    }
}