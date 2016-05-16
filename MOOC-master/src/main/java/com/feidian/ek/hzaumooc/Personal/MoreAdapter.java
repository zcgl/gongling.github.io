package com.feidian.ek.hzaumooc.Personal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.R;

import java.util.List;

/**
 * Created by lenovo on 2016/4/21.
 */
public class MoreAdapter extends BaseAdapter{
    private int layout;
    private List<more> data;
    private Context context;
    public MoreAdapter(int layout,List<more> data,Context context){
        this.layout = layout;
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        more item = (more)data.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)view.findViewById(R.id.name);
            viewHolder.image = (ImageView)view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.image.setImageResource(item.getImage());
        viewHolder.name.setText(item.getName());
        return view;
    }
    class ViewHolder{
        private ImageView image;
        private TextView name;
    }
}
