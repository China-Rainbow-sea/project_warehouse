package com.example.communityepidemicassistant;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//和一级fragment fragment1_info对应的Java类，作用是实现切换顶部选项卡
public class InfoFragment extends Fragment implements View.OnClickListener{

    private List<Fragment> mList = new ArrayList<Fragment>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList.add(new InfoReportFragment());
        mList.add(new InfoQueryFragment());
    }

    //重写onCreateview方法，将对应的fragment1_info动态加载进来
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_info,container,false);
    }

    TextView home_textView;
    TextView query_textView;
    ViewPager home_viewPager;

    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        home_textView=(TextView)view.findViewById(R.id.tv_info_report);
        query_textView=(TextView)view.findViewById(R.id.tv_info_query);
        home_viewPager=(ViewPager)view.findViewById(R.id.mainViewPager_info);
        home_textView.setOnClickListener(this);
        query_textView.setOnClickListener(this);

        //在FragmentManger中设置FragmentPagerAdapter必须使用getChildFragmentManger
        myFragmentPagerAdapter=new MyFragmentPagerAdapter(this.getChildFragmentManager(), mList);

        home_viewPager.setOffscreenPageLimit(2);//ViewPager的缓存为2帧
        home_viewPager.setAdapter(myFragmentPagerAdapter);
        home_viewPager.setCurrentItem(0);
        home_textView.setTextColor(Color.parseColor("#d42517"));

        home_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void changeTextColor(int position){
        if(position==0){
            home_textView.setTextColor(Color.parseColor("#d42517"));
            query_textView.setTextColor(Color.parseColor("#696969"));
        }else if(position==1) {
            query_textView.setTextColor(Color.parseColor("#d42517"));
            home_textView.setTextColor(Color.parseColor("#696969"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_info_report:
                home_viewPager.setCurrentItem(0,true);
                break;
            case R.id.tv_info_query:
                home_viewPager.setCurrentItem(1,true);
                break;
        }
    }




}
