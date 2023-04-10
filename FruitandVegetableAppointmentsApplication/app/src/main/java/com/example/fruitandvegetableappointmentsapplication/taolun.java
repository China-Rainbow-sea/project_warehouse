package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class taolun extends AppCompatActivity {
    private ListView mListView;
    private Button mBtn_insert;
    private EditText wenben;
    private SimpleCursorAdapter mSimpleCursorAdapter;
    private SQLiteDatabase mDbWriter;
    private DBHelper dbhelper;
    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taolun);
        initView();

        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");

        mBtn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                wenben.setText("");
            }
        });

        dbhelper=new DBHelper(this);
        mDbWriter = dbhelper.getWritableDatabase();

        mSimpleCursorAdapter =new SimpleCursorAdapter(taolun.this,R.layout.taolun_item_layout,null,
                new String[]{"userid","pinglun"},new int[]{R.id.id,R.id.pinglunlist}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);
        refreshListview();
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                new AlertDialog.Builder(taolun.this).setTitle("提示").setMessage("是否删除该评论")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData(position);
                    }
                }).setNegativeButton("取消",null).show();
                return true;
            }
        });


    }

    private void initView() {
        mListView =  findViewById(R.id.myListview);
        mBtn_insert =  findViewById(R.id.insert);
        wenben =  findViewById(R.id.xiepinglun);
    }

    public void refreshListview() {
        Cursor mCursor = mDbWriter.query("pinglun", null, null, null, null, null, null);
        mSimpleCursorAdapter.changeCursor(mCursor);
    }
    public void insertData() {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("userid", name);
        mContentValues.put("pinglun", wenben.getText().toString().trim());
        mDbWriter.insert("pinglun", null, mContentValues);
        refreshListview();
    }
    public void deleteData(int positon) {
        Cursor mCursor = mSimpleCursorAdapter.getCursor();
        mCursor.moveToPosition(positon);
        int itemId = mCursor.getInt(mCursor.getColumnIndex("_id"));
        String username = mCursor.getString(mCursor.getColumnIndex("userid"));
        if(username.equals(name)){
            mDbWriter.delete("pinglun", "_id=?", new String[]{itemId + ""});
            refreshListview();
        }else if(username.length()==0){
            Toast.makeText(taolun.this,"您无权删除游客信息！！", Toast.LENGTH_SHORT).show();
        }
        else{
            new AlertDialog.Builder(taolun.this).setTitle("提示").setMessage("您无权限删除该信息！").show();
        }
    }
}