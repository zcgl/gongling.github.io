package com.feidian.ek.hzaumooc.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.R;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ShareAdapter extends BaseAdapter{
    int[] Image={R.mipmap.newqq,R.mipmap.newkongjian,R.mipmap.newweibo,R.mipmap.newweixing};
    String[] name={"QQ","QQ空间","微博","微信"};
    Context activity;
    public ShareAdapter(Context context)
    {
        activity=context;
    }
    @Override
    public int getCount() {
        return 4;
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
        View view= LayoutInflater.from(activity).inflate(R.layout.share_item_gridview,parent,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.share_item_image);
        TextView textView=(TextView)view.findViewById(R.id.share_item_text);
        imageView.setImageResource(Image[position]);
        textView.setText(name[position]);
        return view;
    }
}
