package com.feidian.ek.hzaumooc.download;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.feidian.ek.hzaumooc.Utils.DownloadUtils;

import java.io.File;

/**
 * Created by lenovo on 2016/4/10.
 */
public class Download {
    private static final File sdcard = Environment.getExternalStorageDirectory();
    private static final String path = sdcard.getPath() + "/课程中心";
    private static long downloadId;
    public static void startDownload(Context activity, String url) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
     String serviceString = Context.DOWNLOAD_SERVICE;
        if(DownloadUtils.downloadManager==null) {
            DownloadUtils.downloadManager = (DownloadManager) activity.getSystemService(serviceString);
       }
        String name = getName(url);
     Uri uri=Uri.parse(url);
    File fileName = new File(path, name);
    DownloadManager.Request request = new DownloadManager.Request(uri);
    request.setDestinationUri(Uri.fromFile(fileName));
    request.setTitle(name);
    request.setDescription("现在我的文件正在下载.....");
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    downloadId = DownloadUtils.downloadManager.enqueue(request);
     DownloadUtils.downloadId.add(downloadId);
        DownloadUtils.downloadName.add(name);
 }
    public static String getName(String url){
        url = url.substring(url.lastIndexOf("/")+1);
        return url;
    }
}
