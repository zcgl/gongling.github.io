package com.feidian.ek.hzaumooc.download;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.download.FileM;

import java.util.List;

/**
 * Created by lenovo on 2016/4/4.
 */
public class FileAdapter extends BaseAdapter{
    private List<FileM> data;
    private Context context;
    private int layout;
    public FileAdapter(List<FileM> data, Context context, int layout){
        this.data = data;
        this.context = context;
        this.layout = layout;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileM file = (FileM)data.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)view.findViewById(R.id.name);
            viewHolder.date = (TextView)view.findViewById(R.id.date);
            viewHolder.size = (TextView)view.findViewById(R.id.size);
            viewHolder.image = (ImageView)view.findViewById(R.id.image);
            viewHolder.path = (TextView)view.findViewById(R.id.path);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.image.setImageResource(file.getImage());
        viewHolder.name.setText(file.getName());
        viewHolder.date.setText(file.getDate());
        viewHolder.size.setText(file.getSize());
        viewHolder.path.setText(file.getPath());
        return view;
    }
    class ViewHolder{
        ImageView image;
        TextView name;
        TextView date;
        TextView size;
        TextView path;
    }
}
