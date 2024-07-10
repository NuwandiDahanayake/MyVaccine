package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;

public class SelectHospital extends AppCompatActivity {
    Button nationalhospital, ladyridgeway, nationalkandy, centralhospital, genaralhospital;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hospital);

        ///National Hospital Colombo Button
        nationalhospital = findViewById(R.id.nationalhospital_btn);
        nationalhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(SelectHospital.this, Appoinmtent.class)});
            }
        });

        ///Lady Ridgeway Colombo button
        ladyridgeway = findViewById(R.id.ladyridgeway_btn);
        ladyridgeway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(SelectHospital.this, AppointmentLadyridgeway.class)});
            }
        });

        ///National Hospital Kandy button
        nationalkandy = findViewById(R.id.nationalkandy_btn);
        nationalkandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(SelectHospital.this, AppointmentNational_kandy.class)});
            }
        });

        ///Central Hospital Button
        centralhospital = findViewById(R.id.centralhospital_btn);
        centralhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(SelectHospital.this, Appointment_centralHopital.class)});
            }
        });

        ///Genaral Hospital Button
        genaralhospital = findViewById(R.id.genaralhospital_btn);
        genaralhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(SelectHospital.this, Appointment_GenaralHospital.class)});
            }
        });

        // adding sensor
        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override


            public void onOrientationChanged(int orientation) {

                int epsil = 10;
                int leftscape = 90;
                int rightscape = 270;

                if(epsilCheck(orientation,leftscape,epsil) || epsilCheck(orientation,rightscape,epsil)){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            }

            private boolean epsilCheck(int orientation, int lscapeMode, int epsil){
                return orientation > lscapeMode - epsil && orientation < lscapeMode + epsil;
            }
        };

        orientationEventListener.enable();
    }
}