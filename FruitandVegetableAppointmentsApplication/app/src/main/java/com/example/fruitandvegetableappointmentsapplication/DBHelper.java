package com.example.fruitandvegetableappointmentsapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fruit.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    final String CREATE_TABLE_SQL1="create table if not exists user(_id integer primary key autoincrement ,username,password)";
    final String CREATE_TABLE_SQL2="create table if not exists pinglun(_id integer primary key autoincrement ,userid,pinglun)";
    final String CREATE_TABLE_SQL3="create table if not exists yuyue(_id integer primary key autoincrement ,userid,pinzhong,data)";
    public void onCreate(SQLiteDatabase sqLitedatabase){
        sqLitedatabase.execSQL(CREATE_TABLE_SQL1);
        sqLitedatabase.execSQL(CREATE_TABLE_SQL2);
        sqLitedatabase.execSQL(CREATE_TABLE_SQL3);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
    }


}
