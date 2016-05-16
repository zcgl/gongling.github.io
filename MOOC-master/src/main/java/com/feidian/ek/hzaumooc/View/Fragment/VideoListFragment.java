package com.feidian.ek.hzaumooc.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidian.ek.hzaumooc.Activity.VideoActivity;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.Adapter.ListAdapter;
import com.feidian.ek.hzaumooc.View.ListDivider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoListFragment extends Fragment{

    String[] videos = null;
    final String test1 = "http://211.69.141.12:80/upload/d338206f-4e34-4cdb-a701-9ae4b78fac98.mp4";
    final String test2 = "http://211.69.141.12:80/upload/716a9295-9047-4618-8f39-a6ad41166080.mp4";
    final String test3 = "http://211.69.141.12:80/upload/233d1360-830b-4977-8c2f-ade42e415db5.mp4";
    final String test4 = "http://211.69.141.12:80/upload/aa208733-53d7-4fd1-b9aa-9eafc5efd9c5.mp4";
    final String test5 = "http://211.69.141.12:80/upload/0c08f770-815a-4d51-a7aa-165b3d29b1eb.mp4";

    @Bind(R.id.video_list)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int resid = bundle.getInt("videos");
        if(resid != 0) videos = getResources().getStringArray(resid);

        View root = inflater.inflate(R.layout.fragment_video_layout, container, false);
        ButterKnife.bind(this, root);
        if (videos != null) {
            recyclerView.setAdapter(new videoListAdapter(videos, getActivity()));
            System.out.println("有资源");
        }
        else {
            System.out.println("没有资源");
            recyclerView.setAdapter(new videoListAdapter(getActivity()));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.addItemDecoration(new ListDivider());
        return root;
    }


    class videoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<String> videoList = new ArrayList<String>();
        Context context;

        class VideoHodler extends RecyclerView.ViewHolder {
            @Bind(R.id.listview_ll) LinearLayout ll;
            @Bind(R.id.lv_cover) ImageView cover;
            @Bind(R.id.lv_class_name) TextView name;
            @Bind(R.id.lv_author) TextView author;

            public VideoHodler(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public videoListAdapter(Context context) {
            this.context = context;
            videoList.add(test1);
            videoList.add(test2);
            videoList.add(test3);
            System.out.println(videoList);
        }

        public videoListAdapter(String[] videos, Context context) {
            Collections.addAll(videoList, videos);
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            VideoHodler hodler = new VideoHodler(LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false));
            return hodler;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((VideoHodler) holder).name.setText("第" + (position + 1) + "讲");
            ((VideoHodler) holder).cover.setImageResource(R.mipmap.school);

            ((VideoHodler) holder).ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, VideoActivity.class);
                    System.out.println(videoList.get(position));
                    intent.putExtra("videourl", videoList.get(position));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return videoList.size();
        }
    }
}
