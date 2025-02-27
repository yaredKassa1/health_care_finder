package com.example.health_care_finder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    static int anInt = 1;
    EditText pName,email,credential,confirm;
    TextView resultView;
    Button register,view;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        pName=findViewById(R.id.PersonName);
        email=findViewById(R.id.Email);
        credential=findViewById(R.id.credential);
        confirm=findViewById(R.id.confirm);
        register=findViewById(R.id.Register);
        view=findViewById(R.id.view);
        resultView=findViewById(R.id.resultView);

        db = openOrCreateDatabase("healthcare.db",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists users ( id int not null PRIMARY KEY, name varchar(50), emails varchar(50), nPassword varchar(50), confirmP varchar(50));");



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredential();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "SELECT * FROM users";
                StringBuilder builder = new StringBuilder();
                String fetchName = "", fetchEmail = "", fetchNewP ="", fetchConfirm ="";
                Cursor cursor = db.rawQuery(query, null);
                while (cursor.moveToNext()) {
                    fetchName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    fetchEmail = cursor.getString(2);
                    fetchNewP = cursor.getString(3);
                    fetchConfirm = cursor.getString(4);
                    builder.append("Name:" + fetchName + "\n" + "Phone:" + fetchEmail + "\n" + "Phone:" + fetchNewP + "\n" + "Phone:" + fetchConfirm + "\n\n");
                    resultView.setText(builder.toString());
                }
                cursor.close();
            }
        });
    }



    private void checkCredential() {

        String name=pName.getText().toString();
        String emails=email.getText().toString();
        String nPassword=credential.getText().toString();
        String confirmP=confirm.getText().toString();

        if(name.isEmpty() || name.length()<7){
            showError(pName,"your username is not valid");
        }
        else if(emails.isEmpty() || !emails.contains("@")){
            showError(email,"your email is not valid");
        }
        else if(nPassword.isEmpty() || nPassword.length()<7){
            showError(credential,"your password must be  greater than 7");
        }
        else if(confirmP.isEmpty() || !confirmP.equals(nPassword)){
            showError(confirm,"your password must be  matched");
        }
        else
        {
            db.execSQL("Insert into users values('"+anInt+"','"+name+"','"+emails+"','"+nPassword+"','"+confirmP+"')");
            Toast.makeText(Registration.this,"Registration Success!",Toast.LENGTH_SHORT).show();

            Intent i= new Intent(this,Login.class);
            startActivity(i);
        } anInt++;
    }



    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void hey(View view) {
        Intent i= new Intent(this,Login.class);
        startActivity(i);
    }
}