package com.feidian.ek.hzaumooc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.feidian.ek.hzaumooc.Bean.TeacherSource;
import com.feidian.ek.hzaumooc.Bean.VideoUrl;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.Fragment.ResourceFragment;
import com.feidian.ek.hzaumooc.View.Fragment.VideoListFragment;
import com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.detail_tab) SmartTabLayout smartTabLayout;
    @Bind(R.id.detail_viewpager) ViewPager viewPager;
    @Bind(R.id.background_image)
    ImageView background_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        Bundle send = new Bundle();
        if(getIntent() != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            String classname = bundle.getString("classname");
            System.out.println(classname);
            toolbar.setTitle(classname);

            String imageUrl = bundle.getString("class_image_url");
            Glide.with(this).load(imageUrl).error(R.mipmap.school).into(background_iv);

            VideoUrl videoUrl = getVideoUrl(classname);//取枚举数据
            TeacherSource teacherSource=getTeacherSource(classname);
            if(videoUrl != null) {
                send.putInt("videos", videoUrl.StringID);
            }  else {
                send.putInt("videos", 0);
            }
            if(teacherSource!=null)
            {
                send.putInt("source",teacherSource.StringID);
            }
            else
            {
                send.putInt("source",0);
                send.putString("class_url",bundle.getString("class_url"));
            }


        } else {
            toolbar.setTitle("魅力汉语");
        }

        setSupportActionBar(toolbar);

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        creator.add("教学视频", VideoListFragment.class, send).add("教学文件", ResourceFragment.class, send);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

    }


    private VideoUrl getVideoUrl(String name) {
        VideoUrl videoUrl = null;
        try{
            videoUrl = VideoUrl.valueOf(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoUrl;
    }
    private TeacherSource getTeacherSource(String name)
    {
        TeacherSource teacherSource=null;

        try{
            teacherSource=TeacherSource.valueOf(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherSource;
    }
}
