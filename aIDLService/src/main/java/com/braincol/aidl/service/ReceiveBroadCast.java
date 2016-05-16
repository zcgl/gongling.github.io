package com.braincol.aidl.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by gongling on 2016/5/11.
 */
public class ReceiveBroadCast extends BroadcastReceiver
{
    private String BROADCAST_ACTION="com.braincol.aidl.broadCastFlag";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent.getAction().equals(BROADCAST_ACTION)){
            Intent noteList = new Intent(context,LifeCycleActivity.class);
            //得到广播中得到的数据，并显示出来
            Log.e("BROADCAST_ACTION", intent.getAction() + "*" + intent.getDataString());
            Bundle bundle=intent.getExtras();
            if(bundle!=null) {
                String message = bundle.getString("data");
                RemoteViews view = bundle.getParcelable("remoteView");
                Log.e("BROADCAST_ACTION", message + "*" + view);
            }

            noteList.putExtras(intent);
            noteList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(noteList);
        }



    }

}
