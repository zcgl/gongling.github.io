package com.feidian.ek.hzaumooc.download;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.feidian.ek.hzaumooc.R;

import java.util.List;

/**
 * Created by lenovo on 2016/3/30.
 */

/*
牛逼程序员缩写
跪下
 */
public class DownloadAdapter extends BaseAdapter{
    private List<String> data;
    private Context context;
    private int layout;
    private Handler myHandler;
    public static final int DELETE = 0;
    protected final static String BUNDLE_KEY_LIDATA = "lidata";
    public DownloadAdapter(List<String> data, Handler handler, Context context, int layout){
        this.data = data;
        this.myHandler = handler;
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
        String textName = data.get(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.text_name = (TextView)view.findViewById(R.id.text_name);
            //viewHolder.delete = (ImageView)view.findViewById(R.id.delete);
            viewHolder.text_name.setOnClickListener(new OnItemChildClickListener(DELETE,position));
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.text_name.setText(textName);
        return view;
    }
    class ViewHolder{
        TextView text_name;
        //ImageView delete;
    }
    public class OnItemChildClickListener implements View.OnClickListener {
        // 点击类型索引，对应前面的CLICK_INDEX_xxx
        private int clickIndex;
        // 点击列表位置
        private int position;

        public OnItemChildClickListener(int clickIndex, int position) {
            this.clickIndex = clickIndex;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 创建Message并填充数据，通过mHandle联系Activity接收处理
            Message msg = new Message();
            msg.what = clickIndex;
            msg.arg1 = position;
            Bundle b = new Bundle();
            b.putSerializable(BUNDLE_KEY_LIDATA,data.get(position));
            msg.setData(b);
            myHandler.sendMessage(msg);
        }

    }
}
