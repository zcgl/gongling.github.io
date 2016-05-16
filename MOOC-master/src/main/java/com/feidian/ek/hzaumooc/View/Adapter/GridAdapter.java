package com.feidian.ek.hzaumooc.View.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.ek.hzaumooc.Bean.GoodClass;
import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.Bean.RecommendClass;
import com.feidian.ek.hzaumooc.Bean.YunClass;
import com.feidian.ek.hzaumooc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GridAdapter extends BaseAdapter {
    public final int GRID_SIZE = 4;
    private Context context;
    private int kind;//用来区分课程种类  1为
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context,int kind) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.kind=kind;
    }

    public class ViewHolder {

        @Bind(R.id.grid_cover) ImageView iv;
        @Bind(R.id.grid_title) TextView tv;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            tv.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public int getCount() {
        return GRID_SIZE;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch(kind)
        {
            case MainViewTitle.RECOMMEND:
                Glide.with(context).load(RecommendClass.RecommendClass_JPG[position]).error(R.mipmap.school).into(holder.iv);
                holder.tv.setText(RecommendClass.RecommendClass_CLASSNAME[position]);break;
            case MainViewTitle.GOODCLASS:
                Glide.with(context).load(GoodClass.COUNTRYRESOURSE_JPG[position*5+2]).error(R.mipmap.school).into(holder.iv);
                holder.tv.setText(GoodClass.COUNTRYRESOURSE_CLASSNAME[position*5+2]);break;
            case MainViewTitle.YUNCLASS:
                Glide.with(context).load(YunClass.image[position]).error(R.mipmap.school).into(holder.iv);
                holder.tv.setText(YunClass.name[position]);break;
            default:holder.iv.setBackgroundColor(Color.CYAN);
                holder.tv.setText("hello");
        }
        return convertView;
    }
}
