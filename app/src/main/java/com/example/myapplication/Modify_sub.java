package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.Database.DatabseSub;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Modify_sub extends AppCompatActivity {
    EditText name,payment_date,end_date,price;
    Button btn;
    Calendar payment,date;
    DatabseSub databseSub;
    Intent intent;
    Subscibtor subscibtor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_sub);
        DateFormat dateFormat = new SimpleDateFormat("M/d");
        databseSub = new DatabseSub(this);
        intent = getIntent();
        int Id = intent.getIntExtra("Id",-1);
        subscibtor = new Subscibtor();
        subscibtor = databseSub.getSub(Id);

        name =findViewById(R.id.update_name);
        payment_date =findViewById(R.id.update_payment_date);
        end_date =findViewById(R.id.update_end_date);
        price =findViewById(R.id.update_price);
        btn =findViewById(R.id.update_btn);

        payment_date.setText(dateFormat.format(new Date(subscibtor.getPayDate())).toString());
        end_date.setText(dateFormat.format(new Date(subscibtor.getEndDate())).toString());
        name.setText(subscibtor.getName());
        price.setText(subscibtor.getPayment()+"");

        payment_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Modify_sub.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        payment.set(year,month,day);
                        payment_date.setText((month+1)+"/"+day);
                    }
                },Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                dialog.show();
            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Modify_sub.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.set(year,month,day);
                        end_date.setText((month+1)+"/"+day);
                    }
                },Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                dialog.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscibtor.setName(name.getText().toString());
                if (date!=null){
                    subscibtor.setEndDate(date.getTime().toString());
                }
                if (payment!=null){
                subscibtor.setPayDate(payment.getTime().toString());
                }
                subscibtor.setPayment(Integer.parseInt(price.getText().toString()));
                System.out.println(databseSub.ModifySub(subscibtor)+" modified");
                MyService.Name = subscibtor.getName();
                Intent intent1 = new Intent(getBaseContext(), MyService.class);
                ContextCompat.startForegroundService(getBaseContext(),intent1);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_delete){
            System.out.println("deleted ");
            databseSub.DeleteSub(subscibtor.getId());
            finish();
        }
        return true;
    }
}