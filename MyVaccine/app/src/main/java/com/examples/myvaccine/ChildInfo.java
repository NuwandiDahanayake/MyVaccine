package com.examples.myvaccine;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChildInfo extends AppCompatActivity {
    private EditText childNameEditText;
    private EditText ageEditText;
    private EditText dobEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private EditText bloodGroupEditText;
    private EditText addressEditText;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childinfo);

        childNameEditText = findViewById(R.id.childinfoname_txt);
        ageEditText = findViewById(R.id.ageedit_txt);
        dobEditText = findViewById(R.id.dob_txt);
        weightEditText = findViewById(R.id.weight_txt);
        heightEditText = findViewById(R.id.heightkg_txt);
        bloodGroupEditText = findViewById(R.id.bloodgroup_txt);
        addressEditText = findViewById(R.id.address_txt);
        saveButton = findViewById(R.id.save_btn);

        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChildInfo();
            }
        });
    }

    private void saveChildInfo() {
        String childName = childNameEditText.getText().toString().trim();
        String ageStr = ageEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();
        String weightStr = weightEditText.getText().toString().trim();
        String heightStr = heightEditText.getText().toString().trim();
        String bloodGroup = bloodGroupEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        if (childName.isEmpty() || ageStr.isEmpty() || dob.isEmpty() || weightStr.isEmpty() ||
                heightStr.isEmpty() || bloodGroup.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CHILD_NAME, childName);
        values.put(DatabaseHelper.COLUMN_AGE, age);
        values.put(DatabaseHelper.COLUMN_DATE_OF_BIRTH, dob);
        values.put(DatabaseHelper.COLUMN_WEIGHT, weight);
        values.put(DatabaseHelper.COLUMN_HEIGHT, height);
        values.put(DatabaseHelper.COLUMN_BLOOD_GROUP, bloodGroup);
        values.put(DatabaseHelper.COLUMN_ADDRESS, address);

        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Child info saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save child info", Toast.LENGTH_SHORT).show();
        }

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
