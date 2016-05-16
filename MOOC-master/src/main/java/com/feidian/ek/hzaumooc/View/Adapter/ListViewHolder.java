package com.feidian.ek.hzaumooc.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.NoScrollListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ListViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.item_listview_title)
    TextView title;
    @Bind(R.id.item_listview)
    NoScrollListView listView;
    private ItemOnClickListener itemOnClickListener;

    public ListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setItemOnClickListener(int kind,Context activity)
    {
        if(itemOnClickListener == null)
        {
            itemOnClickListener = new ItemOnClickListener(MainAdapter.LIST,activity,kind);
        }
        else
        {
            itemOnClickListener.setPosition(kind);
        }
        listView.setOnItemClickListener(itemOnClickListener);
    }
}