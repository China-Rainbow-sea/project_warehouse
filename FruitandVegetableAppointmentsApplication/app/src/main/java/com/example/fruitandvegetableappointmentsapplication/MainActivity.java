package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button shouye,yuyue,taolun,wode;
    private ViewHolder holder;
    private Tuijian tuijian;
    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ListView mLvTuijian=findViewById(R.id.tuijian);
        initView();
        tuijian=new Tuijian();
        Newadptor newadptor =new Newadptor();
        mLvTuijian.setAdapter(newadptor);
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");
    }

    private void initView() {
        shouye= findViewById(R.id.shouye);
        yuyue= findViewById(R.id.yuyue);
        taolun= findViewById(R.id.taolun);
        wode=findViewById(R.id.wode);

        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,shucai_activity.class);
                intent1.putExtra("username", name);
                startActivity(intent1);
            }
        });

        taolun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2=new Intent(MainActivity.this,taolun.class);
                intent2.putExtra("username", name);
                startActivity(intent2);
            }
        });

        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MainActivity.this,wode.class);
                intent3.putExtra("username", name);
                startActivity(intent3);
            }
        });
    }

    private class Newadptor extends BaseAdapter{
        @Override
        public int getCount() {
            return Tuijian.getNames().length;
        }

        @Override
        public Object getItem(int i) {
            return Tuijian.getNames()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View cview, ViewGroup viewGroup) {
            if(cview==null){
                cview=View.inflate(MainActivity.this,R.layout.tuijian_item_layout,null);
                holder =new ViewHolder();
                holder.title=cview.findViewById(R.id.tuijian_title);
                holder.news=cview.findViewById(R.id.tuijian_text);
                holder.img=cview.findViewById(R.id.tuijian_img);

                cview.setTag(holder);
            }else{
                holder=(ViewHolder) cview.getTag();
            }

            holder.title.setText(Tuijian.getNames()[i]);
            holder.news.setText(Tuijian.getNews()[i]);
            holder.img.setBackgroundResource(Tuijian.getIcons()[i]);
            return cview;
        }
    }
        private class ViewHolder{
        private TextView title;
        private TextView news;
        private ImageView img;
    }


}