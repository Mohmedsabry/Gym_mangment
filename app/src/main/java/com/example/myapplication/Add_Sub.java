package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.Database.Database;
import com.example.myapplication.Database.DatabseSub;

import java.util.Calendar;

public class Add_Sub extends AppCompatActivity {
    EditText Name,Price,End_date,Date;
    Button btn;
    DatabseSub databseSub;
    Calendar end_date,payment_date;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        Name = findViewById(R.id.Add_name);
        Price = findViewById(R.id.Add_price);
        End_date = findViewById(R.id.Add_end_date);
        Date = findViewById(R.id.Add_date);
        btn = findViewById(R.id.Add_insert);

        databseSub = new DatabseSub(this);

        End_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end_date = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Add_Sub.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
                        end_date.set(year,month,day_of_month);
                        End_date.setText(day_of_month+"/"+(month+1));
                    }
                },Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                dialog.show();
            }
        });
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment_date=Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Add_Sub.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
                        payment_date.set(year,month,day_of_month);
                        Date.setText(day_of_month+"/"+(month+1));
                    }
                },Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                dialog.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscibtor subscibtor =new Subscibtor(Name.getText().toString(),payment_date.getTime().toString(),end_date.getTime().toString(),Integer.parseInt(Price.getText().toString()));
                System.out.println("insert "+databseSub.insert(subscibtor));
                finish();
            }
        });
    }
}