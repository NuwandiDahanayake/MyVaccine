package com.examples.myvaccine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminRegister extends AppCompatActivity {
    private EditText signup_email, password, signup_confirm;
    private Button signup, aregister, adminsignupbtn;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregister);

        // Admin Register button (go to Homepage)
        aregister = findViewById(R.id.aregister);
        aregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegister.this, AdminHomepage.class));
            }
        });

        // Go to Admin Login
        adminsignupbtn = findViewById(R.id.aloginlink);
        adminsignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRegister.this, AdminLogin.class));
            }
        });

        signup_email = findViewById(R.id.adminemai_txt);
        password = findViewById(R.id.adminpw_txt);
        signup_confirm = findViewById(R.id.adminconfirm_txt);
        signup = findViewById(R.id.aregister);
        DB = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signup_email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = signup_confirm.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(AdminRegister.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        boolean checkAdmin = DB.checkAdminEmail(email);
                        if (!checkAdmin) {
                            boolean insert = DB.insertAdmin(email, pass);
                            if (insert) {
                                Toast.makeText(AdminRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AdminHomepage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AdminRegister.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AdminRegister.this, "Admin already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AdminRegister.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
