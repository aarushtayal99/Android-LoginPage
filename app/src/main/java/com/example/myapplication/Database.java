package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Database extends SQLiteOpenHelper {

    public static final String database_name ="LoginActivity";
    public static final String table_name ="login_table";
    public static final String col_1 ="first_name";
    public static final String col_2 ="last_name";
    public static final String col_3 ="contact";
    public static final String col_4 ="email";
    public static final String col_5 ="password";

    public Database(Context context){
        super(context,database_name,null,1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+table_name+"(first_name text, last_name text, contact text, email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insertData(String fname, String lname, String contact_1, String email_1, String pass){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(col_1,fname);
       contentValues.put(col_2,lname);
       contentValues.put(col_3,contact_1);
       contentValues.put(col_4,email_1);
       contentValues.put(col_5,pass);
       long result = db.insert(table_name, null, contentValues);
       db.close();
       if(result == -1)
           return false;
       else
           return true;
    }
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        db.close();
        return res;
    }
    //public boolean checkLogin(String email_1, String pass)
    public boolean checkLogin(String email_1, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM table_name WHERE " + email_1 + " =? AND" + pass + " =?", null);
        //Cursor c = db.rawQuery("SELECT * FROM "  + table_name ,null);

        if(c.getCount() <= 0) {
            c.close();
            db.close();
            return false;
        } else {
            c.close();
            db.close();
            return true;
        }
    }
    /*public String Login(String Username, String Password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String Q = "SELECT * FROM " + Database.table_name + " WHERE "
                +Database.col_4 + " = " + Username
                + " AND " + Database.col_5 + " = " + Password;

        Cursor LoginQuery = db.rawQuery(Q, null);

        if (LoginQuery == null) {
            LoginQuery.close();
            String LoginResult = "Wrong User Name and Password";
            return LoginResult;
        }
        LoginQuery.moveToFirst();
        String LoginResult = LoginQuery.getString(3);
        LoginQuery.close();
        //return LoginResult;
        return Toast.makeText(Database.this, LoginResult, Toast.LENGTH_LONG).show();*/
    }


