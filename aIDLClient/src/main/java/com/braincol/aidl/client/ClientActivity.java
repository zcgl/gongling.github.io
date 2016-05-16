package com.braincol.aidl.client;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.braincol.aidl.service.Beauty;
import com.braincol.aidl.service.RemoteBeauty;

public class ClientActivity extends Activity implements OnClickListener {
    private final static String TAG = "ClientActivity";
    TextView textView;
    Button btn_start, btn_bind, btn_unbind, btn_stop;
    Button btn_getAllInfo;
    String actionName = "com.braincol.aidl.remote.webpage";
    RemoteBeauty remoteWebPage = null;
    String allInfo = null;
    boolean isBinded = false;
    private String BROADCAST_ACTION="com.braincol.aidl.broadCastFlag";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textView);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_bind = (Button) findViewById(R.id.btn_bind);
        btn_unbind = (Button) findViewById(R.id.btn_unbind);
        btn_stop = (Button) findViewById(R.id.btn_stop);


        btn_getAllInfo = (Button) findViewById(R.id.btn_allinfo);

        btn_start.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        btn_getAllInfo.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        if (isBinded) {
            Log.d(TAG, "unbind");
            unbindService(connection);
            isBinded = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (isBinded) {
            Log.d(TAG, "unbind");
            unbindService(connection);
            isBinded = false;
        }
    }

    private forActivity mCallback = new forActivity.Stub() {
        public void performAction(String toast) throws RemoteException {
            Toast.makeText(ClientActivity.this, "toast is called:" + toast, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RemoteViews view = new RemoteViews(getPackageName(), R.layout.remote_view);
                    view.setTextViewText(R.id.remote_text, "Android开发网欢迎您");

                    Intent intent = new Intent(BROADCAST_ACTION);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("remoteView", view);
                    bundle.putString("data", "client传的数据");
                    intent.putExtras(bundle);
                    ClientActivity.this.sendBroadcast(intent);
                }
            },5000);

        }
    };

    @TargetApi(16)
    private void sendFoldNotification(RemoteViews view) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        builder.setAutoCancel(true);
        builder.setContentTitle("通知一个");
        //用RemoteViews来创建自定义Notification视图
        RemoteViews remoteViews = view;
        Notification notification = builder.build();
        //指定展开时的视图
        notification.bigContentView = remoteViews;
        notificationManager.notify(1, notification);
    }

    private class MyServiceConnection implements ServiceConnection {

        /**
         * 当调用bindService方法后就会回调Activity的onServiceConnected，在这个方法中会向Activity中传递一个IBinder的实例，Acitity需要保存这个实例
         * 在Service中需要创建一个实现IBinder的内部类(这个内部类不一定在Service中实现，但必须在Service中创建它)。
         * 在OnBind（）方法中需返回一个IBinder实例，不然onServiceConnected方法不会调用。
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected连接中...");
            remoteWebPage = RemoteBeauty.Stub.asInterface(service);
            if (remoteWebPage == null) {
                textView.setText("bind service failed!");
                return;
            }

            try {
                isBinded = true;
                btn_bind.setText("绑定");
                textView.setText("已连接");
                remoteWebPage.registerTestCall(mCallback);
                Beauty beauty =null;// remoteWebPage.getBeauty();
                allInfo = remoteWebPage.getCurrentPageUrl()+"\n" + remoteWebPage.getAllInfo() +"\n"+ beauty;
                btn_getAllInfo.setEnabled(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected...");
        }

    }

    MyServiceConnection connection = new MyServiceConnection();

    @Override
    public void onClick(View v) {
        if (v == this.btn_start) {
            start();
        } else if (v == this.btn_stop) {
            stop();
        } else if (v == this.btn_bind) {
            bind();
            isBinded = true;
            textView.setText("已连接");
        } else if (v == this.btn_unbind) {
            unbind();
            isBinded = false;
            textView.setText("已断开连接");
            //同进程调用
            try {
                mCallback.performAction("client toast");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (v == this.btn_getAllInfo) {
            textView.setText(allInfo);
        }

    }

    public void start() {
        Intent intent = new Intent(actionName);
        intent.setAction("com.braincol.aidl.remote.webpage");//action
        intent.setPackage("com.braincol.aidl.service");//package
        startService(intent);
    }

    public void stop() {
        Intent intent = new Intent(actionName);
        intent.setAction("com.braincol.aidl.remote.webpage");//action
        intent.setPackage("com.braincol.aidl.service");//package
        stopService(intent);
    }

    public void bind() {
        if (!isBinded) {
            Intent intent = new Intent(actionName);
            intent.setAction("com.braincol.aidl.remote.webpage");//action
            intent.setPackage("com.braincol.aidl.service");//package
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unbind() {
        if (isBinded) {
            unbindService(connection);
        }
    }
}