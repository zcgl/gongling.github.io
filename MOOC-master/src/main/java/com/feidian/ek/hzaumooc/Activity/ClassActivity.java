package com.feidian.ek.hzaumooc.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.Adapter.ClassAdapter;
import com.feidian.ek.hzaumooc.View.ListDivider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/29.
 */
public class ClassActivity extends BaseActivity {

    @Bind(R.id.main_list) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
        int kind=bundle.getInt("type");//获取要显示的课程序号
        setContentView(R.layout.class_main);
        ButterKnife.bind(this);

        toolbar.setTitle(MainViewTitle.MAIN[kind - 1]);
        /*toolbar.setLogo(this.getResources().getDrawable(R.mipmap.undo));*/
        setSupportActionBar(toolbar);//设置标题栏

        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(new ClassAdapter(this,kind));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListDivider());


    }
}
