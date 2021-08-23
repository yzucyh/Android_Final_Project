package com.example.mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (username TEXT primary key, password TEXT,weight TEXT, height TEXT,exercise TEXT, MON TEXT, TUE DATE, WED DATE, THU DATE, FRI DATE, SAT DATE, SUN DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String he, String we, String exe, String fn){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("height", he);
        contentValues.put("weight", we);
        contentValues.put("exercise", exe);
        contentValues.put("MON", fn);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor readAllData(){
        String query = "SELECT * FROM " + "users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean checkusername(String username){         //checkusername function
        SQLiteDatabase MyDB = this.getWritableDatabase();  //set the DB connection
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? ",new String[]{username} );
        if (cursor.getCount() > 0)           //Search the table, if the database has one, getCount+1
            return true;
        else
            return false;
    }

    public Boolean insertFoodData(String foodname)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValuesd = new ContentValues();
        contentValuesd.put("MON", foodname);
        long result = MyDB.update("users", contentValuesd, "username="+"username", null );
        Log.d("status", String.valueOf(result));
        if (result == -1)
            return false;
        else
            return true;
    }

}
