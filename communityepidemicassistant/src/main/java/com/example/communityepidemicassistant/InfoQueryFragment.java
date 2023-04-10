package com.example.communityepidemicassistant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.List;

//二级Fragment，用于社区人员疫情信息查询的InfoQueryFragment，实现把疫情信息载入本地数据库的功能
public class InfoQueryFragment extends Fragment {

    SimpleAdapter mSimpleAdapter;
    SimpleCursorAdapter mAdapter;
    private SearchView search;
    private ListView listView_info;
    private InfoDBOpenHelper mInfoDBOpenHelper;
    SQLiteDatabase dbinfo;
    private List mData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_info_query,container,false);

        search=(SearchView)view.findViewById(R.id.sv_search);
        search.setIconified(false);
        listView_info=(ListView)view.findViewById(R.id.listView_info);
        init();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        refresh();
        mAdapter=new SimpleCursorAdapter(getContext(),R.layout.listitem_query,
                null,
                new String[]{"name","date","ifFever","ifTouch","idCard","phone","address"},
                new int[]{R.id.tv_listitem_infoName,R.id.tv_listitem_infoDate,R.id.tv_listitem_infoIfFever, R.id.tv_listitem_infoIfTouch,
                        R.id.tv_listitem_infoID,R.id.tv_listitem_infoPhone,R.id.tv_listitem_infoAddress},
                        0);
        listView_info.setAdapter(mAdapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){

                    Cursor cursor=dbinfo.query("info",
                            null,
                            "name like '%"+newText+"%'",
                            null,
                            null,
                            null,
                            "date DESC");
                    mAdapter.swapCursor(cursor);
                }else {
                    mAdapter.swapCursor(null);
                }
                return false;
            }
        });
    }



    private void  init(){
        mInfoDBOpenHelper=new InfoDBOpenHelper(getContext());
        dbinfo=mInfoDBOpenHelper.getReadableDatabase();
    }

//    private List<Map<String,Object>> getData() {
//        List<Map<String, Object>> list = new ArrayList<>();
//        //查询数据
//        Cursor c = dbinfo.query("info", //query函数返回一个游标c
//                null,
//                null,  //筛选条件
//                null,  //筛选值
//                null,
//                null,
//                "date DESC");
//
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            for (int i = 0; i < c.getCount(); i++) {
//                String name = c.getString(c.getColumnIndexOrThrow("name"));
//                String date = c.getString(c.getColumnIndexOrThrow("date"));
//                String ifFever = c.getString(c.getColumnIndexOrThrow("ifFever"));
//                String ifTouch = c.getString(c.getColumnIndexOrThrow("ifTouch"));
//                String id = c.getString(c.getColumnIndexOrThrow("idCard"));
//                String phone= c.getString(c.getColumnIndexOrThrow("phone"));
//                String address = c.getString(c.getColumnIndexOrThrow("address"));
//                //把值添加到listview的数据集中
//                Map<String, Object> map = new HashMap<>();
//                map.put("name", name);   //"xx"是数据库中的数据，xx是把数据库中的数据拿出来变成的string
//                map.put("date", date);
//                map.put("ifFever", ifFever);
//                map.put("ifTouch", ifTouch);
//                map.put("idCard", id);
//                map.put("phone", phone);
//                map.put("address", address);
//                list.add(map);
//                c.moveToNext();
//            }
//        }
//        c.close();
//        dbinfo.close();
//        return list;
//    }

    private void refresh(){
        onCreate(null);
    }
}
