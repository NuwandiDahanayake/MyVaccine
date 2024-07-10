package com.examples.myvaccine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class AdminLogin extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +          //at least 1 digit
                    "(?=.*[a-z])" +          //at least 1 lower case letter
                    "(?=.*[A-Z])" +          //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +        //any letter
                    "(?=.*[@#$%^&+=])" +      //at least 1 special character
                    "(?=\\S+$)" +             //no white spaces
                    ".{4,}" +                 //at least 4 characters
                    "$");

    private EditText textInputEmail;
    private EditText textInputPassword;
    private EditText username, password;
    private Button btnlogin, adminsignupbtn;
    private DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        // Admin Login button (go to Admin Register)
        adminsignupbtn = findViewById(R.id.signupadmin_btn);
        adminsignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this, AdminRegister.class));
            }
        });

        textInputEmail = findViewById(R.id.adminemail);
        textInputPassword = findViewById(R.id.adminpw);

        username = findViewById(R.id.adminemail);
        password = findViewById(R.id.adminpw);
        btnlogin = findViewById(R.id.alogin);
        DB = new DatabaseHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(AdminLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkuserpass = DB.checkEmailPassword(user, pass);
                    if (checkuserpass) {
                        Toast.makeText(AdminLogin.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminHomepage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AdminLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        String input = "Email: " + textInputEmail.getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
