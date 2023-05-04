package com.example.myapplication.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Database extends SQLiteAssetHelper{

    private static String Name="Admins.db";
    private static String TABEL="Admin";
    private  String USERNAME="username";
    private  String PASSWORD="password";
    private static int Version=2;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public Database(Context context) {
        super(context, Name,null, Version);
    }

    public Boolean Insert(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();// map -> key ,value
        cv.put(USERNAME,user.getUsername());
        cv.put(PASSWORD,user.getPassword());
        return sqLiteDatabase.insert(TABEL,null,cv)>-1;// insert has not done -1
    }
    @SuppressLint("Range")
    public ArrayList<User> getUsers(){
        ArrayList<User> arrayList = new ArrayList<>();// null pointer execption
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from "+TABEL+"",null);
        if (c.moveToFirst()){// null-> false notnull->true
            do {
                arrayList.add(new User(c.getString(c.getColumnIndex(USERNAME)),c.getString(c.getColumnIndex(PASSWORD))));
            }while (c.moveToNext());// not null -> true
        }
        return arrayList;
    }
}
