package com.example.health_care_finder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email,credential;
    Button login;
    private HealthcareDatabaseHelper DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.Email);
        credential=findViewById(R.id.credential);
        login=findViewById(R.id.login);
        DatabaseHelper =new HealthcareDatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredential();
            }
        });

    }

    private void checkCredential() {


        String emails=email.getText().toString();
        String nPassword=credential.getText().toString();


        if (DatabaseHelper.checkUser(emails, nPassword)) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Login.this, "You're not our member", Toast.LENGTH_SHORT).show();
        }
    }


}