package com.feidian.ek.hzaumooc.Activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.Personal.PersonalActivity;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.Suggestion.SuggestActivity;
import com.feidian.ek.hzaumooc.Utils.DownloadUtils;
import com.feidian.ek.hzaumooc.View.Adapter.ShareAdapter;
import com.feidian.ek.hzaumooc.View.ListDivider;
import com.feidian.ek.hzaumooc.View.Adapter.MainAdapter;
import com.feidian.ek.hzaumooc.download.DowloadAcivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    PopupWindow popupWindow;

    @Bind(R.id.main_list) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        recyclerView.setAdapter(new MainAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ListDivider());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent =new Intent(this, PersonalActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Intent intent =new Intent(this,DowloadAcivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent =new Intent(this,HistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            showPopuptWindow();
        } else if (id == R.id.nav_send) {
                 Intent intent  = new Intent(MainActivity.this, SuggestActivity.class);
                 startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void showPopuptWindow()
    {
        LayoutInflater layoutInflater=getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.share_gridview,null);
        GridView gridview=(GridView)view.findViewById(R.id.share_grid);
        gridview.setAdapter(new ShareAdapter(this));
        popupWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ClosePop();
            }
        });



        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        ColorDrawable dw=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        popupWindow.showAtLocation( recyclerView,
                Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams layoutParams=this.getWindow().getAttributes();
        layoutParams.alpha=0.5f;
        this.getWindow().setAttributes(layoutParams);
    }
    void ClosePop()
    {
        if(popupWindow!=null ){
        popupWindow.dismiss();
        popupWindow=null;
        }
        WindowManager.LayoutParams params=this.getWindow().getAttributes();
        params.alpha=1.0f;
        this.getWindow().setAttributes(params);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
