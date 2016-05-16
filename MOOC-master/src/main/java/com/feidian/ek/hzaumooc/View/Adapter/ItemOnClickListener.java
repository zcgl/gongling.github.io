package com.feidian.ek.hzaumooc.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.feidian.ek.hzaumooc.Activity.DetailActivity;
import com.feidian.ek.hzaumooc.Activity.MainActivity;
import com.feidian.ek.hzaumooc.Bean.ClassRank;
import com.feidian.ek.hzaumooc.Bean.GoodClass;
import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.Bean.RecommendClass;
import com.feidian.ek.hzaumooc.Bean.YunClass;
/**
 * Created by Administrator on 2016/4/8.
 */
public class ItemOnClickListener implements AdapterView.OnItemClickListener{
    private int type;//type注明是Gird或是List
    private Context activity;
    private int kind;//kind表明具体的类别
    public ItemOnClickListener(int type,Context activity,int kind)//type注明是Gird或是List，kind表明具体的类别
    {
        this.activity=activity;
        this.kind=kind;
        this.type=type;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        intent.setClass(activity, DetailActivity.class);
        Bundle bundle = new Bundle();

        if(type==MainAdapter.GIRD)
        {
            switch(kind)
            {
                case MainViewTitle.RECOMMEND:
                    bundle.putString("classname", RecommendClass.RecommendClass_CLASSNAME[position]);
                    //bundle.putString("cn", RecommendClass.RecommendClass_CN[position]);
                    bundle.putString("class_image_url", RecommendClass.RecommendClass_JPG[position]);
                    bundle.putString("class_url",RecommendClass.RecommendClass_WEB[position]);
                    break;
                case MainViewTitle.GOODCLASS:
                    bundle.putString("classname", GoodClass.COUNTRYRESOURSE_CLASSNAME[position*5+2]);
                    //bundle.putString("cn", GoodClass.COUNTRYRESOURSE_CN[position*5+2]);
                    bundle.putString("class_image_url", GoodClass.COUNTRYRESOURSE_JPG[position*5+2]);
                    bundle.putString("class_url",GoodClass.COUNTRYRESOURSE_WEB[position*5+2]);
                    break;
                case MainViewTitle.YUNCLASS:
                    bundle.putString("classname", YunClass.name[position]);
                    //bundle.putString("cn", YunClass.cn[position]);
                    bundle.putString("class_image_url",YunClass.image[position]);
                    bundle.putString("class_url",YunClass.web[position]);
                    break;
                default:
                    Toast.makeText(activity,"list", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        else if(type == MainAdapter.LIST)
        {
            switch(kind)
            {
                case MainViewTitle.RANK:
                    bundle.putString("classname", RecommendClass.RecommendClass_CLASSNAME[position]);
                    //bundle.putString("cn", RecommendClass.RecommendClass_CN[position]);
                    bundle.putString("class_image_url", RecommendClass.RecommendClass_JPG[position]);
                    bundle.putString("class_url",RecommendClass.RecommendClass_WEB[position]);
                    break;
                case MainViewTitle.GOODCLASS_1:
                    bundle.putString("classname", GoodClass.COUNTRYVIDEO_CLASSNAME[position]);
                    //bundle.putString("cn", GoodClass.COUNTRYVIDEO_CLASSNAME[position]);
                    bundle.putString("class_image_url", GoodClass.COUNTRYVIDEO_JPG[position]);
                    bundle.putString("class_url",GoodClass.COUNTRYVIDEO_WEB[position]);
                    break;
                case MainViewTitle.GOODCLASS_2:
                    bundle.putString("classname", GoodClass.COUNTRYRESOURSE_CLASSNAME[position]);
                    //bundle.putString("cn", GoodClass.COUNTRYVIDEO_CN[position]);
                    bundle.putString("class_image_url", GoodClass.COUNTRYRESOURSE_JPG[position]);
                    bundle.putString("class_url",GoodClass.COUNTRYRESOURSE_WEB[position]);
                    break;
                case MainViewTitle.GOODCLASS_3:
                    bundle.putString("classname", GoodClass.PROVINCEGOODCLASS_CLASSNAME[position]);
                    //bundle.putString("cn", GoodClass.PROVINCEGOODCLASS_CN[position]);
                    bundle.putString("class_image_url", GoodClass.PROVINCEGOODCLASS_JPG[position]);
                    bundle.putString("class_url",GoodClass.PROVINCEGOODCLASS_WEB[position]);
                    break;
                case MainViewTitle.YUNCLASS:
                    bundle.putString("classname", YunClass.name[position]);
                    //bundle.putString("cn", YunClass.cn[position]);
                    bundle.putString("class_image_url",YunClass.image[position]);
                    bundle.putString("class_url",YunClass.web[position]);
                    break;
                case MainViewTitle.RECOMMEND:
                    bundle.putString("classname", GoodClass.COUNTRYRESOURSE_CLASSNAME[position]);
                    //bundle.putString("cn", GoodClass.COUNTRYRESOURSE_CN[position]);
                    bundle.putString("class_image_url", GoodClass.COUNTRYRESOURSE_JPG[position]);
                    bundle.putString("class_url",GoodClass.COUNTRYRESOURSE_WEB[position]);
                    break;
                default:
                    Toast.makeText(activity,"list", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        System.out.println("进来了");
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    public void setPosition(int kind)
    {
        this.kind=kind;
    }
}
