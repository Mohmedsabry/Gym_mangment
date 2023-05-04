package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.Database;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText UserName,Password;
    Button Login,Register;
    Database database;
    ArrayList <User>arrayList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        database = new Database(this);// this getbasecontext()
        arrayList =new ArrayList<>();

        UserName = findViewById(R.id.login_username);
        Password = findViewById(R.id.login_password);
        Login = findViewById(R.id.Login_btn_Login);
        Register = findViewById(R.id.Login_btn_Register);

        Password.setTransformationMethod(new PasswordTransformationMethod());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = database.getUsers();
                String username = UserName.getText().toString();
                String password = Password.getText().toString();
                if (!username.equals("")&&!password.equals("")){
                    for (int i=0;i<arrayList.size();i++){
                        Log.d("TAG", username+" "+password+" "+arrayList.get(i).getUsername()+" "+arrayList.get(i).getPassword());;
                        if(username.equals(arrayList.get(i).getUsername())&&password.equals(arrayList.get(i).getPassword())){
                            Log.d("Login", "yes");
                            startActivity(new Intent(getBaseContext(), Home.class));
                        }
                    }
                    Log.d("Login", "no "+arrayList.size());
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(),Register.class),2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}