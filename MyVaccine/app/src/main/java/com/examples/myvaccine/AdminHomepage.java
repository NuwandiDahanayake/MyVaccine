package com.examples.myvaccine;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
public class AdminHomepage extends AppCompatActivity {
    Button addhospital, getchild, approvalhospital;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

 ///Press Add Hospital going to Admin Hospital Add page (Add Hospital Button)
        addhospital = findViewById(R.id.addhospitalbtn);
        addhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(AdminHomepage.this, AdminhospitalAdd.class)});
            }
        });

 ///Get Child Button
        getchild = findViewById(R.id.getchildhome_btn);
        getchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(AdminHomepage.this, ViewchildDetails.class)});
            }
        });

 ///Approval Hospital Button
        approvalhospital = findViewById(R.id.approvalhospital_btn);
        approvalhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(AdminHomepage.this, AddapprovalHospital.class)});
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