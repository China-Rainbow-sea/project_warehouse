package com.example.communityepidemicassistant;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MenoFragment extends Fragment {
    private EditText et_meno;
    private FloatingActionButton fab_save;
    private FloatingActionButton fab_del;
    private FileOutputStream fos = null;
    private Boolean ifclose=true;

    //重写onCreateView 将fragment_meno动态加载进来
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_meno,container,false);

        et_meno=(EditText)view.findViewById(R.id.et_mono);
        fab_save=(FloatingActionButton)view.findViewById(R.id.fab_1);
        fab_del=(FloatingActionButton)view.findViewById(R.id.fab_2);
        onload();

        et_meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_meno.setCursorVisible(true);
                if(ifclose){
                    closeKeybord(et_meno,getContext());
                    ifclose=false;
                }else {
                    ifclose=true;
                }
            }
        });


        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    fos = getActivity().openFileOutput("txt", Context.MODE_PRIVATE);//数据保存到文件
                    String text = et_meno.getText().toString();
                    fos.write(text.getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(fos!=null){
                            fos.flush();
                            Toast.makeText(getContext(),"保存成功！",Toast.LENGTH_SHORT).show();
                            hideInputMethod(getContext(), getView());
                            fos.close();
                            et_meno.setCursorVisible(false);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        fab_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatdialog();
                FileOutputStream fos = null;
                try{
                    fos = getActivity().openFileOutput("txt", Context.MODE_PRIVATE);
                    String text = "";
                    fos.write(text.getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(fos!=null){
                            fos.flush();
                            fos.close();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }

    public void onload(){
        FileInputStream fis = null;
        try{
            fis = getActivity().openFileInput("txt");//在fragment中，调用getActivity()才能调用openFileInput
            if(fis.available()==0){
                return;
            }else{
                byte[] con = new byte[fis.available()];
                while(fis.read(con)!=-1){

                }
                et_meno.setText(new String(con));
                et_meno.setSelection(et_meno.getText().length());
                et_meno.setCursorVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void creatdialog() {
        AlertDialog.Builder b=new AlertDialog.Builder(getContext());
        //设置提示框内容
        b.setMessage("确认清空备忘录内容么？再想想？");
        //设置标题栏
        b.setTitle("提示");
        b.setIcon(R.drawable.warns);
        b.setPositiveButton("确认",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //点确定时隐藏此对话框并将EditText清空
                dialog.dismiss();
                et_meno.setText("");
                hideInputMethod(getContext(), getView());
            }
        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消时只隐藏对话框
                dialog.dismiss();
            }
        });
        b.create().show();
    }

    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

}

