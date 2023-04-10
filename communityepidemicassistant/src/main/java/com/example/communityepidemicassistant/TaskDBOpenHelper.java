package com.example.communityepidemicassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public TaskDBOpenHelper(Context context){
        super(context,"db_task",null,2);
        db=getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS task(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "date DATE," +
                "abstract TEXT," +
                "detail TEXT," +
                "iffinished TEXT," +
                "img TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS task");
        onCreate(db);
    }

    //增删改查
    public void add(String task_title,String task_date,String task_abstract,String task_detail,String task_iffinished,int icon){
        java.sql.Date sqlDate = java.sql.Date.valueOf(task_date);
        db.execSQL("INSERT INTO task (title,date,abstract,detail,iffinished,img) VALUES(?,?,?,?,?,?)",new Object[]{task_title,task_date,task_abstract,task_detail,task_iffinished,String.valueOf(icon)});
    }
    public void delete(String task_title){
        db.execSQL("DELETE FROM task WHERE title ="+task_title);
    }
    public void update(String iffinished,int icon,String title){
        db.execSQL("UPDATE task SET iffinished = ?,img=? WHERE title=?",new Object[]{iffinished,String.valueOf(icon),title});
    }
    public ArrayList<Task> getAllData(){//把表中所有数据放到list中
        ArrayList<Task> list = new ArrayList<Task>();
        Cursor cursor = db.query("task",null,null,null,null,null,"title DESC");
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String task_abstract=cursor.getString(cursor.getColumnIndex("abstract"));
            String detail=cursor.getString(cursor.getColumnIndex("detail"));
            String iffinished=cursor.getString(cursor.getColumnIndex("iffinished"));
            String img=cursor.getString(cursor.getColumnIndexOrThrow("img"));
            list.add(new Task(title,date,task_abstract,detail,iffinished,img)); //把task对象保存至list中
        }
        return list;
    }
}
