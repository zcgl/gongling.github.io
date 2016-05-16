package com.feidian.ek.hzaumooc.View.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.Activity.VideoActivity;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.Adapter.ListAdapter;
import com.feidian.ek.hzaumooc.download.DowloadAcivity;
import com.feidian.ek.hzaumooc.download.Download;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import 	android.os.Handler;
import java.util.logging.LogRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResourceFragment extends Fragment {

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println(resourceList);
            recyclerView.setAdapter(new SourceListAdapter( getActivity()));
        }
    };
    String[] source;
    List<Map<String,String>> resourceList = new ArrayList<Map<String,String>>();
    PopupWindow popupWindow;
    @Bind(R.id.video_list) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        int resid = bundle.getInt("source");
        if(resid != 0) source = getResources().getStringArray(resid);
        System.out.println("资源"+resid);
        View root = inflater.inflate(R.layout.fragment_video_layout, container, false);
        ButterKnife.bind(this, root);
        if(source!=null) {
            recyclerView.setAdapter(new SourceListAdapter(source, getActivity()));
        }
        else
        {
            final String class_url=bundle.getString("class_url");
            new Thread(){
                @Override
                public void run() {
                    Resource resource=new Resource(class_url,resourceList);
                    resource.findResource("教学资料");
                    handler.sendEmptyMessage(0);
                }
            }.start();


        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
    class SourceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        static final int WEB=1;
        static final int STATIC=2;

        List<String> videoList= new ArrayList<String>(); ;
        Context context;
        int kind=0;

        class VideoHodler extends RecyclerView.ViewHolder {
            @Bind(R.id.listview_ll)
            LinearLayout ll;
            @Bind(R.id.lv_cover)
            ImageView cover;
            @Bind(R.id.lv_class_name)
            TextView name;
            @Bind(R.id.lv_author) TextView author;

            public VideoHodler(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                cover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }
        class NoResourceHolder extends RecyclerView.ViewHolder
        {
            @Bind(R.id.down_load_no)
            TextView noresource;

            public NoResourceHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

        public SourceListAdapter(Context context) {
            this.context = context;
            kind=WEB;
        }

        public SourceListAdapter(String[] videos, Context context) {
            Collections.addAll(videoList, videos);
            this.context = context;
            kind=STATIC;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if(videoList.size()!=0 || resourceList.size()!=0)
            { VideoHodler hodler;
              hodler = new VideoHodler(LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false));
            return hodler;}
            else
            {

                NoResourceHolder hodler=new NoResourceHolder(LayoutInflater.from(context).inflate(R.layout.down_load,parent,false));
                return hodler;
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof VideoHodler) {
                if (kind == STATIC) resourceStatic(holder, position);
                else {
                    resourceWeb(holder, position);

                }
                ((VideoHodler) holder).ll.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showPopupwindows();
                        return false;
                    }
                });
            }
            if(holder instanceof NoResourceHolder)
            {
                ((NoResourceHolder)holder).noresource.setText("老师很懒...静请期待...");
            }
        }
        void resourceStatic(RecyclerView.ViewHolder holder, final int position)
        {
            ((VideoHodler) holder).name.setText("第" + (position + 1) + "讲");
            ((VideoHodler) holder).cover.setImageResource(R.mipmap.ppt2);
        }
        void resourceWeb(RecyclerView.ViewHolder holder, final int position)
        {
            String s=resourceList.get(position).get("name");
            ((VideoHodler) holder).name.setText(s);
            if(s.endsWith("swf"))
            {
                ((VideoHodler) holder).cover.setImageResource(R.mipmap.sef);
            }
            else if(s.endsWith("rar"))
            ((VideoHodler) holder).cover.setImageResource(R.mipmap.rar);
            else
                ((VideoHodler) holder).cover.setImageResource(R.mipmap.ppt2);
        }


        @Override
        public int getItemCount() {
            if(kind==STATIC && videoList.size()!=0) return videoList.size();
            else if(kind==WEB && resourceList.size()!=0) {return resourceList.size();}
            else return 1;
        }
    }
    private void showPopupwindows()
    {
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.resourcedownload,null);
        Button botton= (Button) view.findViewById(R.id.resource_cancel);
        Button download=(Button) view.findViewById(R.id.resource_download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download.startDownload(getContext(),"http://211.69.141.12/upload/20150120115818706.ppt");
                popupWindow.dismiss();
                popupWindow=null;
                Intent intent=new Intent();
                intent.setClass(getContext(), DowloadAcivity.class);
                getActivity().startActivity(intent);
            }
        });
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing())
                {
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            }
        });
        popupWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        ColorDrawable colorDrawable=new ColorDrawable(0x00000000);//设置全透明
       popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM,0,0);
        WindowManager.LayoutParams layoutParams=getActivity().getWindow().getAttributes();
        layoutParams.alpha=0.5f;
        getActivity().getWindow().setAttributes(layoutParams);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                turnWhite();
            }
        });
    }
    private void turnWhite()
    {
        if(popupWindow.isShowing())
        {
            popupWindow.dismiss();
            popupWindow=null;
        }
        WindowManager.LayoutParams layoutParams=getActivity().getWindow().getAttributes();
        layoutParams.alpha=1f;
        getActivity().getWindow().setAttributes(layoutParams);
    }
}
