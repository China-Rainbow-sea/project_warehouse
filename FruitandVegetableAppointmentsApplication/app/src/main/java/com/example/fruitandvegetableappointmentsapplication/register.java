package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    DBHelper dbHelper;
    EditText username;
    EditText password;
    EditText checkpassword;
    Button register;
    Button inlogin;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper=new DBHelper(this);
        db = dbHelper .getWritableDatabase();
        initView();

        inlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=username.getText().toString();
                String pwd=password.getText().toString();
                String pwd2=checkpassword.getText().toString();
                if (!(name.isEmpty()||pwd.isEmpty()))
                {

                    if ((pwd).equals(pwd2)) {
                        Cursor cursor = db.rawQuery(
                                "select count(*) from user where username = '"
                                        + name + "'",
                                null);
                        cursor.moveToNext();
                        int count = cursor.getInt(0);
                        if (count == 0) {
                            ContentValues values = new ContentValues();
                            values.put("username", name);
                            values.put("password", pwd);
                            db.insert("user", null, values);
                            Toast.makeText(register.this, "注册成功！",
                                    Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                            checkpassword.setText("");
                        }
                        else {
                            Toast.makeText(register.this, "您注册的用户名已存在！",
                                    Toast.LENGTH_SHORT).show();
                        }
                        cursor.close();
                    }
                    else {
                        Toast.makeText(register.this, "您两次输入的密码不一样！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else if(name.isEmpty()){
                    Toast.makeText(register.this, "用户名不能为空",
                            Toast.LENGTH_SHORT).show();
                }
                else if (pwd.isEmpty()){
                    Toast.makeText(register.this, "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        checkpassword=findViewById(R.id.password2);
        register=findViewById(R.id.confirm);
        inlogin=findViewById(R.id.back);
    }
}