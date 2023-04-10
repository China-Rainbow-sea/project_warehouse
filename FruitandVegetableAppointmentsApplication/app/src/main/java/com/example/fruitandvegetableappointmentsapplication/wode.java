package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class wode extends AppCompatActivity {
    Button login;
    Button more;
    TextView username2;
    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode);

        initView();
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");

        username2.setText(name);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten1=new Intent(wode.this,login.class);
                startActivity(inten1);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  AlertDialog.Builder(wode.this).setMessage("更多功能，尽情期待").show();
            }
        });
    }

    private void initView() {
        login=findViewById(R.id.login);
        more=findViewById(R.id.more);
        username2=findViewById(R.id.username2);
    }
}