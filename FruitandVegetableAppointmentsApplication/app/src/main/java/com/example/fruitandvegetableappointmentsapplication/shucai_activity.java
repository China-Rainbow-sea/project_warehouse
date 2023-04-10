package com.example.fruitandvegetableappointmentsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class shucai_activity extends AppCompatActivity {
    private ViewHolder holder;
    private Tuijian tuijian;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shucai);

        Intent intent=getIntent();
        name=intent.getStringExtra("username");
        ListView mLvshucai=findViewById(R.id.yuyue);

        tuijian=new Tuijian();

        Yuyueadaptor yuyueadaptor=new Yuyueadaptor();
        mLvshucai.setAdapter(yuyueadaptor);

        mLvshucai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent3=new Intent(shucai_activity.this,yuyuexuanze.class);
                intent3.putExtra("username",name);
                startActivity(intent3);
            }
        });

    }
    private class Yuyueadaptor extends BaseAdapter{
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
                cview=View.inflate(shucai_activity.this,R.layout.tuijian_item_layout,null);
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