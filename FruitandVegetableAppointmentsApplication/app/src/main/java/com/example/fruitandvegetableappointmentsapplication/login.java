package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    DBHelper dbHelper;
    EditText username;
    EditText password;
    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new DBHelper(this);
        initView();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=username.getText().toString();
                String pwd=password.getText().toString();


                Cursor cursor=dbHelper.getReadableDatabase().query("user",null,"username=? and password=?",new String[]{name,pwd},null,null,null);
                ArrayList<Map<String,String>> resultList=new ArrayList<Map<String,String>>();

                while (cursor.moveToNext()){
                    Map<String,String> map=new HashMap<String, String>();
                    map.put("name",cursor.getString(1));
                    resultList.add(map);
                }
                if (resultList==null || resultList.size()==0){
                    Toast.makeText(login.this,"账户或密码错误!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(login.this,"登录成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(login.this,MainActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbHelper!=null){
            dbHelper.close();
        }
    }



}