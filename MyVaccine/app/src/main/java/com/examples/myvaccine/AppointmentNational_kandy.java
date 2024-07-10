package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppointmentNational_kandy extends AppCompatActivity {
    Button nationalkandymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_national_kandy);

        ///National Hospital Kandy location button
        nationalkandymap = findViewById(R.id.kandylocation_btn);
        nationalkandymap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent( AppointmentNational_kandy.this, Nationalkandy_Map.class)});
            }
        });
    }
}