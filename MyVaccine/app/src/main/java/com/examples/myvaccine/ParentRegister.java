package com.examples.myvaccine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentRegister extends AppCompatActivity {

    EditText signup_email, password, signup_confirm;
    Button signup, pregister, ploginlink ;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentregister);

        ///Parent Register button(go to Homepage)
        pregister = findViewById(R.id.pregister);
        pregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentRegister.this, Homepage.class));
            }
        });

        ///Go to the Parent Login
        ploginlink = findViewById(R.id.ploginlink);
        ploginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentRegister.this, ParentLogin.class));
            }
        });

        signup_email = (EditText) findViewById(R.id.parentregiemail);
        password = (EditText) findViewById(R.id.parentregipw);
        signup_confirm = (EditText) findViewById(R.id.parentconfirmregipw);
        signup = (Button) findViewById(R.id.pregister);
        DB = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signup_email.getText().toString();
                String pass = password.getText().toString();
                String repass = signup_confirm.getText().toString();

                if(email.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(ParentRegister.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkEmail(email);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(email, pass );
                            if(insert==true){
                                Toast.makeText(ParentRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Homepage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(ParentRegister.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(ParentRegister.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ParentRegister.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}