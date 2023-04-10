package com.example.communityepidemicassistant;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private TaskAllFragment taskAllFragment;
    private InfoFragment infoFragment;
    private Fragment[] fragments;
    private int lastfragment;   //用于记录上个选择的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //让底部导航栏不被软键盘顶起
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();

    }

    //初始化函数 初始化2个fragment和bottomNavigationView和一个初始显示的fragment
    public void init() {
        taskAllFragment=new TaskAllFragment();
        infoFragment=new InfoFragment();
        fragments=new Fragment[]{taskAllFragment,infoFragment};
        lastfragment=0; //表示上个被选中的导航栏item
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,taskAllFragment).show(taskAllFragment).commit();
        mBottomNavigationView = findViewById(R.id.bv_bottomNavigation);

        //mbottomNavigationView 绑定的一个点击监听的函数changeFragment
        mBottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);

    }

    //监听函数changeFragment
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment=new BottomNavigationView.OnNavigationItemSelectedListener() {
        //对点击的item的id做判断，然后通过switchFragment函数来进行界面的操作
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId())
            {
                case R.id.menu_task:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.menu_info:
                {
                    if (lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    //switchFragment函数,隐藏上个fragment显示选中的fragment
    private void switchFragment(int lastfragment,int index){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);  //隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}
