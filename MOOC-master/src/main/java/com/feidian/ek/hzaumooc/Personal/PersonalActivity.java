package com.feidian.ek.hzaumooc.Personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.feidian.ek.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/4/21.
 */
public class PersonalActivity extends Activity{
    private List<more> data = new ArrayList<>();
    private ListView listView = null;
    private MoreAdapter adapter;
    private String[] name = {"消息提醒","历史纪录","反馈建议","关于我们","退出登录"};
    private int[] image = {R.mipmap.news,R.mipmap.record,R.mipmap.suggest,R.mipmap.about,R.mipmap.out};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        listView = (ListView)super.findViewById(R.id.full);
        init();
    }
    public void init(){
        for(int i = 0;i<name.length;i++){
            data.add(new more(name[i],image[i]));
        }
        adapter = new MoreAdapter(R.layout.person_item,data,this);
        listView.setAdapter(adapter);
    }
}
