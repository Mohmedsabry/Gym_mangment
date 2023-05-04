package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.Database.DatabseSub;
import com.example.myapplication.Database.PaymentFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    BottomNavigationView nav;
    FloatingActionButton btn;
    DatabseSub databseSub;
    Payment_Adpter adpter;
    HomeAdpter homeAdpter;
    ArrayList<Subscibtor>Home,Payment;
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nav = findViewById(R.id.BottomNav);
        btn = findViewById(R.id.Home_btn_add);

        databseSub = new DatabseSub(this);
        Home= new ArrayList<>();
        Payment= new ArrayList<>();
        Home = databseSub.getHome();
        Payment =databseSub.getPayment();
        adpter=new Payment_Adpter(Payment, new Payment_Adpter.PaymentListener() {
            @Override
            public void Onclick(int id) {
                System.out.println("it is Payment id : "+id);
                Subscibtor subscibtor = new Subscibtor();
                subscibtor = databseSub.getSub(id);
                System.out.println(subscibtor.toString());
                Intent intent = new Intent(getBaseContext(),Modify_sub.class);
                intent.putExtra("Id",id);
                startActivityForResult(intent,6);
            }
        });
        homeAdpter = new HomeAdpter(Home, new HomeAdpter.HomeListener() {
            @Override
            public void Onclick(int id) {
                System.out.println("it is home id : "+id);
                Subscibtor subscibtor= new Subscibtor();
                subscibtor = databseSub.getSub(id);
                System.out.println(subscibtor.toString());
                Intent intent = new Intent(getBaseContext(),Modify_sub.class);
                intent.putExtra("Id",id);
                startActivityForResult(intent,5);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getBaseContext(),Add_Sub.class),5);
            }
        });
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Menu_Home:{
                        Home = databseSub.getHome();
                        homeAdpter.setArrayList(Home);
                        HomeFrag homeFrag = HomeFrag.newInstance(homeAdpter);
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame,homeFrag).commit();
                        break;
                    }
                    case R.id.Menu_Payment:{
                        Payment = databseSub.getPayment();
                        adpter.setArrayList(Payment);
                        PaymentFrag paymentFrag = PaymentFrag.newInstance(adpter);
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame,paymentFrag).commit();
                        break;
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (nav.getSelectedItemId()==R.id.Menu_Home){
            Home = databseSub.getHome();
            homeAdpter.setArrayList(Home);
        }else if(nav.getSelectedItemId()==R.id.Menu_Payment){
            Payment = databseSub.getPayment();
            adpter.setArrayList(Payment);
        }

    }

}