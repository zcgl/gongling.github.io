package com.feidian.ek.hzaumooc.download;


import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.Utils.DownloadUtils;
import com.feidian.ek.hzaumooc.download.DownloadAdapter;
import java.io.File;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GameFragment extends Fragment {
	private ListView listView;
	private List<String> list;
	private DownloadAdapter adapter;
	private View view = null;
	private ProgressBar progressBar = null;
	private TextView size = null;
	private RelativeLayout relativeLayout = null;
	private List<Integer> downloadList = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.down_load, null);
		relativeLayout =  (RelativeLayout)view.findViewById(R.id.relative);
		listView = (ListView) view.findViewById(R.id.download);
		list = new ArrayList<String>();
		recordAndDelete();
		//初始化下载队列
		for(int i = 0;i<DownloadUtils.downloadId.size();i++) {
			list.add(DownloadUtils.downloadName.get(i));
		}
		adapter = new DownloadAdapter(list, mHandle, getActivity(), R.layout.list_data);
		listView.setAdapter(adapter);
		isFind();
		downloadHandler.postDelayed(runnable, 1000);
		return view;
	}
	//判断是否还有下载任务
	public void isFind(){
		if(list.size()==0){
			relativeLayout.setVisibility(View.VISIBLE);
		}else{
			relativeLayout.setVisibility(View.INVISIBLE);
		}
	}
	//对取消下载队列的监听处理
	private Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case DownloadAdapter.DELETE:
					int position = msg.arg1;
					  DownloadUtils.downloadManager.remove(DownloadUtils.downloadId.get(position).longValue());
					  onUpdateView(position);
					break;
				default:
					break;
			}
		}
	};
	//定时更新下载进度条
	public Runnable runnable = new Runnable() {
		@Override
		public void run() {
			for(int i = 0;i<DownloadUtils.downloadId.size();){
				view = listView.getChildAt(i);
				if(view!=null) {
					progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
					size = (TextView) view.findViewById(R.id.size);
					DownloadManager.Query query = new DownloadManager.Query().setFilterById(DownloadUtils.downloadId.get(i));
					Cursor cursor = DownloadUtils.downloadManager.query(query);
					int now = 0, total = 0;
					if (cursor != null && cursor.moveToFirst()) {
						now = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
						total = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
						size.setText(change(now, total));
						progressBar.setProgress(now);
						progressBar.setMax(total);
						if (now == total) {
							onUpdateView(i);
						} else {
							i++;
						}
					}
				}
				else i++;
			}
			downloadHandler.postDelayed(this,1000);
		}
	};
	//通过handler启动runnable
	private Handler downloadHandler = new Handler();
	//记录下载完成的Id并且进行移除
	public void recordAndDelete(){
		//记录下载完成的ID
		for(int i = 0;i<DownloadUtils.downloadId.size();i++) {
			DownloadManager.Query query = new DownloadManager.Query().setFilterById(DownloadUtils.downloadId.get(i));
			Cursor cursor = DownloadUtils.downloadManager.query(query);
			int now = 0,total = 0;
			if (cursor != null && cursor.moveToFirst()) {
				now = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
				total = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
				if(now==total){
					downloadList.add(i);
				}
			}
		}
		//对下载完成的队列进行移除
		for(int i = 0;i<downloadList.size();i++){
			DownloadUtils.downloadId.remove(downloadList.get(i).intValue());
			DownloadUtils.downloadName.remove(downloadList.get(i).intValue());
		}
		downloadList.clear();
	}
	//对下载单位进行变换
	public String change(float now,float total){
		String has, all;
		if (total / 1024 >= 1) {
			total = (float) total / 1024;
			all = "k";
			if (total / 1024 >= 1) {
				total = total / 1024;
				all = "m";
				if (total / 1024 >= 1) {
					total = total / 1024;
					all = "g";
				}
			}
		} else {
			all = "b";
		}
		if (now/ 1024 >= 1) {
			now = (float) now / 1024;
			has = "k";
			if (now / 1024 >= 1) {
				now = now / 1024;
				has = "m";
				if (now / 1024 >= 1) {
					now = now / 1024;
					has = "g";
				}
			}
		} else {
			has = "b";
		}
		DecimalFormat fnum = new DecimalFormat("##0.0");
		String hasSizea = fnum.format(now);
		String allSizea = fnum.format(total);
		String x = hasSizea + has + "/" + allSizea + all;
		return x;
	}
	//更新下载完成后或取消下载队列时的下载列表
	public void onUpdateView(int i){
		DownloadUtils.downloadId.remove(i);
		DownloadUtils.downloadName.remove(i);
		list.remove(i);
		adapter.notifyDataSetChanged();
		isFind();
	}
}

