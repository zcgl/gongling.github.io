package com.feidian.ek.hzaumooc.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.ek.hzaumooc.Bean.ClassRank;
import com.feidian.ek.hzaumooc.Bean.GoodClass;
import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.Bean.YunClass;
import com.feidian.ek.hzaumooc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luoba on 2016/3/30.
 */
public class ListAdapter extends BaseAdapter{

    private int LIST_SIZE = 3;//记录总共的item数
    private int kind=0;//记录第几个list
    private Context context;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    public ListAdapter(Context context,int kind)//主要用于黄宇的精品课程页面
    {
        this(context);
        this.kind=kind;
        if(kind==1)//国家级精品视频
        {
            LIST_SIZE= GoodClass.COUNTRYVIDEO_JPG.length;
        }
        else if (kind==2)//国家级精品资源
        {
            LIST_SIZE=GoodClass.COUNTRYRESOURSE_JPG.length;
        }
        else if(kind==3)//省级精品资源
        {
            LIST_SIZE=GoodClass.PROVINCEGOODCLASS_JPG.length;
        }
        else if(kind==4)
        {
            LIST_SIZE=4;
        }
        else//一般情况
        {
            LIST_SIZE=3;
        }

    }
    public ListAdapter(Context context,int kind,int type)//用于云课堂页面
    {
        this(context);
        this.kind=kind;//5用于第二个界面的云课堂,6用于推荐课程
        if(type== MainViewTitle.YUNCLASS)
        {
            LIST_SIZE=YunClass.name.length;
        }
    }

    class ViewHolder {

        @Bind(R.id.lv_cover) ImageView cover;
        @Bind(R.id.lv_class_name) TextView name;
        @Bind(R.id.lv_author) TextView author;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public int getCount() {
        return LIST_SIZE;
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
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (kind)
        {
            case 0:holder.name.setText("微生物");
                holder.author.setText("赵兵");
                break;
            case 1://精品课程——国家级精品视频
                Glide.with(context).load(GoodClass.COUNTRYVIDEO_JPG[position]).error(R.mipmap.school).into(holder.cover);
                holder.name.setText(GoodClass.COUNTRYVIDEO_CLASSNAME[position]);
                holder.author.setText(GoodClass.COUNTRYVIDEO_TEACHERNAME[position]);
                break;
            case 2://精品课程——国家级精品资源
                Glide.with(context).load(GoodClass.COUNTRYRESOURSE_JPG[position]).error(R.mipmap.school).into(holder.cover);
                holder.name.setText(GoodClass.COUNTRYRESOURSE_CLASSNAME[position]);
                holder.author.setText(GoodClass.COUNTRYRESOURSE_TEACHERNAME[position]);
                break;
            case 3://精品课程——省级精品资源
                Glide.with(context).load(GoodClass.PROVINCEGOODCLASS_JPG[position]).error(R.mipmap.school).into(holder.cover);
                holder.name.setText(GoodClass.PROVINCEGOODCLASS_CLASSNAME[position]);
                holder.author.setText(GoodClass.PROVINCEGOODCLASS_TEACHERNAME[position]);
                break;
            case 4://主页--课程排名
                Glide.with(context).load(ClassRank.ClassRank_JPG[position]).error(R.mipmap.school).into(holder.cover);
                holder.name.setText(ClassRank.ClassRank_CLASSNAME[position]);
                holder.author.setText(ClassRank.ClassRank_TEACHER[position]);
                break;
            case 5://第二个界面的云课堂
                Glide.with(context).load(YunClass.image[position]).error(R.mipmap.school).into(holder.cover);
                holder.name.setText(YunClass.name[position]);
                holder.author.setText(YunClass.teacher[position]);
                break;
            case 6:
            default:holder.name.setText("微生物");
                holder.author.setText("赵兵");
                break;
        }
        return convertView;
    }
}
