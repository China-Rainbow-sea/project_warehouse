package com.example.communityepidemicassistant;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "tasksId";
    SQLiteDatabase dbr;
    private TaskDBOpenHelper mTaskDBOpenHelper;
    private Button bt_back;
    private TextView tv_taskdetail_detail;
    private TextView tv_taskdetail_date;
    private TextView tv_taskdetail_title;
    private Button bt_taskdetail_finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();
        bt_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int tasksId;///////////////////////
        tasksId = getIntent().getExtras().getInt(EXTRA_ID);///////////////////////
        Cursor c = dbr.query("task", //query函数返回一个游标c
                null,
                null,  //筛选条件
                null,  //筛选值
                null,
                null,
                "iffinished,date DESC");
        getValueByColumnId(c,tasksId);

        bt_taskdetail_finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr=mTaskDBOpenHelper.getWritableDatabase();
                creatdialog();
            }
        });
    }

    public void creatdialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warns);
        builder.setTitle("提示");
        builder.setMessage("确认完成任务？");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            public void onClick(DialogInterface dialog, int which) {
                //点确定时修改数据库并隐藏此对话框
                    mTaskDBOpenHelper.update("yes",R.drawable.finished,tv_taskdetail_title.getText().toString());
                    Toast.makeText(getBaseContext(),"确认成功！",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
            }
        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消时只隐藏对话框
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void init() {
        mTaskDBOpenHelper = new TaskDBOpenHelper(this);//////////////////////////
        dbr = mTaskDBOpenHelper.getReadableDatabase();////////////////
        bt_back = (Button) findViewById(R.id.bt_taskdetail_back);
        tv_taskdetail_title = (TextView) findViewById(R.id.tv_taskdetail_title);
        tv_taskdetail_date = (TextView) findViewById(R.id.tv_taskdetail_date);
        tv_taskdetail_detail = (TextView) findViewById(R.id.tv_taskdetail_details);
        bt_taskdetail_finished=(Button)findViewById(R.id.bt_taskdetail_finish);
    }

    public void getValueByColumnId(Cursor c, int id){
            c.moveToPosition(id);
            tv_taskdetail_title.setText(c.getString(c.getColumnIndexOrThrow("title")));
            tv_taskdetail_date.setText(c.getString(c.getColumnIndexOrThrow("date")));
            tv_taskdetail_detail.setText(c.getString(c.getColumnIndexOrThrow("detail")));
    }
}
