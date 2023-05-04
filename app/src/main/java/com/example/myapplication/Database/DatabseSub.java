package com.example.myapplication.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Subscibtor;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabseSub extends SQLiteAssetHelper {
    private static final String NAME="subuser.db";
    private static final String TABEL="data";
    private static final String ID="id";
    private static final String NAME_OF_USER="name";
    private static final String PAYMENT_PRICE="payment_price";
    private static final String START_DATE="start_date";
    private static final String END_DATE="end_date";
    private static final String PAYMENT_DATE="payment_date";

    private static int VERSION =1;
    public DatabseSub(Context context) {
        super(context, NAME, null, VERSION);
    }
    public Boolean insert(Subscibtor subscibtor){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_OF_USER,subscibtor.getName());
        cv.put(PAYMENT_DATE,subscibtor.getPayDate());
        cv.put(END_DATE,subscibtor.getEndDate());
        cv.put(PAYMENT_PRICE,subscibtor.getPayment());
        return sqLiteDatabase.insert(TABEL,null,cv)>-1;
    }
    @SuppressLint("Range")
    public ArrayList<Subscibtor> getHome(){
        ArrayList <Subscibtor>arrayList= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from "+TABEL+" ",null);
        if (c.moveToFirst()){
            do {
                arrayList.add(new Subscibtor(c.getInt(c.getColumnIndex(ID)),c.getString(c.getColumnIndex(NAME_OF_USER)),c.getString(c.getColumnIndex(PAYMENT_DATE)),c.getString(c.getColumnIndex(END_DATE)),c.getInt(c.getColumnIndex(PAYMENT_PRICE))));
            }while (c.moveToNext());
        }
        return arrayList;
    }
    @SuppressLint("Range")
    public ArrayList<Subscibtor> getPayment(){
        ArrayList <Subscibtor>arrayList= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from "+TABEL+" ",null);
        if (c.moveToFirst()){
            do {
                arrayList.add(new Subscibtor(c.getInt(c.getColumnIndex(ID)),c.getString(c.getColumnIndex(NAME_OF_USER)),c.getString(c.getColumnIndex(PAYMENT_DATE)),c.getInt(c.getColumnIndex(PAYMENT_PRICE))));
            }while (c.moveToNext());
        }
        return arrayList;
    }
    public int DeleteSub(int id ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABEL,""+ID+"= "+id+"",null);
    }
    public int ModifySub(Subscibtor subscibtor ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_OF_USER,subscibtor.getName());
        cv.put(START_DATE,subscibtor.getStartDate());
        cv.put(PAYMENT_PRICE,subscibtor.getPayment());
        cv.put(PAYMENT_DATE,subscibtor.getPayDate());
        cv.put(END_DATE,subscibtor.getEndDate());
        return sqLiteDatabase.update(TABEL,cv,""+ID+"="+subscibtor.getId()+"",null);
    }
    @SuppressLint("Range")
    public Subscibtor getSub(int id){
        Subscibtor subscibtor = new Subscibtor();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+TABEL+" where "+ID+"="+id+"",null);
        if (cursor.moveToFirst()){
            do {
                subscibtor = new Subscibtor(id,cursor.getString(cursor.getColumnIndex(NAME_OF_USER)),"",cursor.getString(cursor.getColumnIndex(END_DATE)),cursor.getString(cursor.getColumnIndex(PAYMENT_DATE)),cursor.getInt(cursor.getColumnIndex(PAYMENT_PRICE)));
            }while (cursor.moveToNext());
        }
        return subscibtor;
    }

}
