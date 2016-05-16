package com.feidian.ek.hzaumooc.View.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.feidian.ek.hzaumooc.Activity.ClassActivity;
import com.feidian.ek.hzaumooc.Bean.MainViewTitle;

/**
 * Created by huangyu on 2016/4/7.
 */
public class MoreOnClickListener implements View.OnClickListener {
    Context activity;
    int position;
    public MoreOnClickListener(int position,Context context)
    {
        activity=context;
        this.position=position;
    }
    @Override
    public void   onClick(View v) {
        if(position == MainViewTitle.GOODCLASS){
        Intent intent =new Intent(activity,ClassActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("type", MainViewTitle.GOODCLASS);
        intent.putExtras(bundle);
        activity.startActivity(intent);}
        else if(position == MainViewTitle.YUNCLASS)
        {
            Intent intent =new Intent(activity,ClassActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type", MainViewTitle.YUNCLASS);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
        else if(position == MainViewTitle.RECOMMEND)
        {
            Intent intent =new Intent(activity,ClassActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("type", MainViewTitle.RECOMMEND);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }

    }
    public void setPosition(int position) {
        this.position = position;
    }
}
