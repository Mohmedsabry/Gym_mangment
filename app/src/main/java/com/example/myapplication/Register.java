package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.Database;

public class Register extends AppCompatActivity {
    EditText UserName,Password;
    Button Register;
    Database database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);// jave -> xml

        database = new Database(getBaseContext());

        UserName = findViewById(R.id.Register_username);
        Password = findViewById(R.id.Register_password);
        Register = findViewById(R.id.Register_btn_register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =UserName.getText().toString();
                String password =Password.getText().toString();
                if (!username.equals("")&&!password.equals("")){
                    User user = new User(username,password);
                    database.Insert(user);
                    finish();// kill activity
                }
            }
        });
    }
}