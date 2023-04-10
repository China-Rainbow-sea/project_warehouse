package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class yuyuexuanze extends AppCompatActivity{
    private Button button1,button2,button3,button4,backbutton;
    TextView text1,text2,text3,text4;
    private int year;
    private int month;
    private int day;
    private SQLiteDatabase mDbWriter;
    private DBHelper dbhelper;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyuexuanze);

        dbhelper = new DBHelper(this);
        mDbWriter = dbhelper.getWritableDatabase();
        Intent intent=getIntent();
        name=intent.getStringExtra("username");
        initView();
        Calendar myCalendar = Calendar.getInstance(Locale.CHINA);
        Date myDate = new Date();
        myCalendar.setTime(myDate);

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text1.setText("未预约");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(yuyuexuanze.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text1.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="1";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text1.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                    }
                    },year,month,day);
                dpd.show();
                    }
        });
        myCalendar.setTime(myDate);//为Calendar对象设置时间为当前日期

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text2.setText("未预约");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(yuyuexuanze.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text2.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="2";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text2.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        myCalendar.setTime(myDate);//为Calendar对象设置时间为当前日期

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text3.setText("未预约");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(yuyuexuanze.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text3.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="3";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text3.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        myCalendar.setTime(myDate);

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text4.setText("未预约");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(yuyuexuanze.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text4.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="4";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text4.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                    }
                },year,month,day);
                dpd.show();
            }
        });
    backbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2=new Intent(yuyuexuanze.this,MainActivity.class);
            intent2.putExtra("username",name);
            startActivity(intent2);
        }
    });
    }

    private void initView() {
        button1 =  findViewById(R.id.yuyuebutton1);
        button2 =  findViewById(R.id.yuyuebutton2);
        button3 =  findViewById(R.id.yuyuebutton3);
        button4 =   findViewById(R.id.yuyuebutton4);
        backbutton=findViewById(R.id.fanhui);

        text1 =findViewById(R.id.yuyuetext1);
        text2 =findViewById(R.id.yuyuetext2);
        text3 =findViewById(R.id.yuyuetext3);
        text4 =findViewById(R.id.yuyuetext4);
    }
}