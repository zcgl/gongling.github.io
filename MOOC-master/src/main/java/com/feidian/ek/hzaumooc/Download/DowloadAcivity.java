package com.feidian.ek.hzaumooc.download;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Dialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.feidian.ek.hzaumooc.download.GameFragment;
import com.feidian.ek.hzaumooc.download.APPFragment;
import com.feidian.ek.hzaumooc.R;

import java.util.ArrayList;

import static android.app.AlertDialog.*;

public class DowloadAcivity extends FragmentActivity {
    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;
    private TextView tab_game;
    private TextView tab_app;
    private int line_width;
    private View line;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        tab_game = (TextView) findViewById(R.id.tab_game);
        tab_app = (TextView) findViewById(R.id.tab_app);
        line = findViewById(R.id.line);
        // 初始化TextView动画
        // ViewPropertyAnimator.animate(tab_app).scaleX(1.2f).setDuration(0);
        tab_app.animate().scaleX(1.2f).setDuration(0); //设置动画，字扩大到1.2倍
        //ViewPropertyAnimator.animate(tab_app).scaleY(1.2f).setDuration(0);
        tab_app.animate().scaleY(1.2f).setDuration(0);

        fragments = new ArrayList<Fragment>();//填充两个fragment
        fragments.add(new APPFragment());
        fragments.add(new GameFragment());
        line_width = getWindowManager().getDefaultDisplay().getWidth()
                / fragments.size();//计算指示栏的长度
        line.getLayoutParams().width = line_width;//设置指示栏的长度
        line.requestLayout();//??
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                changeState(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                float tagerX = arg0 * line_width + arg2 / fragments.size();
               // ViewPropertyAnimator.animate(line).translationX(tagerX)
                 //       .setDuration(0);
                line.animate().translationX(tagerX).setDuration(0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        tab_game.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });

        tab_app.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
    }
    /* 根据传入的值来改变状态 */
    private void changeState(int arg0) { //设置选择的变化动画
        Message msg = new Message();
        msg.what = 1;
        handler.sendMessage(msg);
        if (arg0 == 0) {
            tab_app.setTextColor(getResources().getColor(R.color.green));
            tab_game.setTextColor(getResources().getColor(R.color.gray_white));
            //ViewPropertyAnimator.animate(tab_app).scaleX(1.2f).setDuration(200);
            tab_app.animate().scaleX(1.2f).setDuration(200);
            //ViewPropertyAnimator.animate(tab_app).scaleY(1.2f).setDuration(200);
            tab_app.animate().scaleY(1.2f).setDuration(200);
            //ViewPropertyAnimator.animate(tab_game).scaleX(1.0f).setDuration(200);
            tab_game.animate().scaleX(1.0f).setDuration(200);
            //ViewPropertyAnimator.animate(tab_game).scaleY(1.0f).setDuration(200);
            tab_game.animate().scaleY(1.0f).setDuration(200);
        } else {
            tab_game.setTextColor(getResources().getColor(R.color.green));
            tab_app.setTextColor(getResources().getColor(R.color.gray_white));
            //ViewPropertyAnimator.animate(tab_app).scaleX(1.0f).setDuration(200);
            tab_app.animate().scaleX(1.0f).setDuration(200);
            //ViewPropertyAnimator.animate(tab_app).scaleY(1.0f).setDuration(200);
            tab_app.animate().scaleY(1.0f).setDuration(200);
           // ViewPropertyAnimator.animate(tab_game).scaleX(1.2f).setDuration(200);
            tab_game.animate().scaleX(1.2f).setDuration(200);
            //ViewPropertyAnimator.animate(tab_game).scaleY(1.2f).setDuration(200);
            tab_game.animate().scaleY(1.2f).setDuration(200);
        }
    }
  public void setHandler(Handler handler){
      this.handler = handler;
  }
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        /*if (keyCode == KeyEvent.KEYCODE_BACK )
        {
          Dialog dialog = new AlertDialog.Builder(DowloadAcivity.this).setIcon(R.mipmap.ic_launcher)
                              .setTitle("确定退出么？").setMessage("建议下载完成后退出")
                              .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      finish();
                                  }
                              })
                              .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {

                                  }
                              }).create();
            dialog.show();

        }
        return super.onKeyDown(keyCode,event);
    }*/
}
