package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    Button bookhospital, addchildhome, viewapproval, contactus, whyvaccination_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bookhospital = findViewById(R.id.bookhospital);
        bookhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Homepage.this, SelectHospital.class)});
            }
        });

        addchildhome = findViewById(R.id.addchildhome);
        addchildhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Homepage.this, ChildInfo.class)});
            }
        });

        viewapproval = findViewById(R.id.viewapprovalbtn);
        viewapproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Homepage.this, ViewApproval.class)});
            }
        });

        contactus = findViewById(R.id.contactusbtn);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Homepage.this, ContactUs.class)});
            }
        });

        whyvaccination_btn = findViewById(R.id.whyvaccination_btn);
        whyvaccination_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Homepage.this, Why_Vaccinationpage.class)});
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