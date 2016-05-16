package com.example.liuwangshu.mynotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MyNotificationActivity extends Activity implements View.OnClickListener {
    private TextView tv_nomal;
    private TextView tv_fold;
    private TextView tv_hang;
    private TextView tv_bigpic;
    private TextView tv_bigtext;
    private TextView tv_biginbox;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioGroup radioGroup;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);
        tv_nomal = (TextView) findViewById(R.id.tv_nomal);
        tv_fold = (TextView) findViewById(R.id.tv_fold);
        tv_hang = (TextView) findViewById(R.id.tv_hang);
        tv_bigpic = (TextView) findViewById(R.id.tv_bigpic);
        tv_bigtext = (TextView) findViewById(R.id.tv_bigtext);
        tv_biginbox = (TextView) findViewById(R.id.tv_biginbox);

        radioButton1 = (RadioButton) findViewById(R.id.rb_public);
        radioButton2 = (RadioButton) findViewById(R.id.rb_private);
        radioButton2 = (RadioButton) findViewById(R.id.rb_secret);
        radioGroup = (RadioGroup) findViewById(R.id.rg_all);

        tv_nomal.setOnClickListener(this);
        tv_fold.setOnClickListener(this);
        tv_hang.setOnClickListener(this);
        tv_bigpic.setOnClickListener(this);
        tv_bigtext.setOnClickListener(this);
        tv_biginbox.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void selectNotofovatiomLevel(Notification.Builder builder) {
        if (Build.VERSION.SDK_INT < 21){
            return;
        }
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
                break;
            case R.id.rb_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
            default:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;

        }
    }

    private void sendNomalNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.lanucher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        selectNotofovatiomLevel(builder);
        notificationManager.notify(0, builder.build());

    }

    private void sendFoldNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");
        selectNotofovatiomLevel(builder);
        //用RemoteViews来创建自定义Notification视图
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
        Notification notification = builder.build();
        //指定展开时的视图
        notification.bigContentView = remoteViews;
        notificationManager.notify(1, notification);
    }



    private void sendHangNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");
        selectNotofovatiomLevel(builder);
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this, MyNotificationActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);

        notificationManager.notify(2, builder.build());
    }

    /**
     *  1.NotificationCompat.BigPictureStyle 大图片风格：详情区域包含一个256dp高度的位图

     2.NotificationCompat.BigTextStyle ：显示一个大的文字块

     3.NotificationCompat.InboxStyle  收件箱风格：显示多行文字
     */
    private void sendBigPicNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("大图片风格");
        builder.setStyle(
                new NotificationCompat.BigPictureStyle()
                        .setBigContentTitle("6 new message")
                        .setSummaryText("mtwain@android.com")
                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher))
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.foldleft))
        );
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this, MyNotificationActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        notificationManager.notify(3, builder.build());
    }


    private void sendBigTextNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("大文字风格");
        builder.setStyle(
                new NotificationCompat.BigTextStyle()
                        .bigText("SDEFSDdfs诗兴大发")
                        .setBigContentTitle("6 new message")
                        .setSummaryText("mtwain@android.com")
        );
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this, MyNotificationActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);

        notificationManager.notify(4, builder.build());
    }
    private void sendInboxNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.lanucher));
        builder.setAutoCancel(true);
        builder.setContentTitle("收件箱风格");
        builder.setStyle(
                new NotificationCompat.InboxStyle()
                        .addLine("M.Twain (Google+) Haiku is more than a cert...")
                        .addLine("M.Twain Reminder")
                        .addLine("M.Twain Lunch?")
                        .addLine("M.Twain Revised Specs")
                        .addLine("M.Twain ")
                        .addLine("Google Play Celebrate 25 billion apps with Goo..")
                        .addLine("Stack Exchange StackOverflow weekly Newsl...")
                        .setBigContentTitle("6 new message")
                        .setSummaryText("mtwain@android.com")
        );
        //设置点击跳转

        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationManager.notify(5, builder.build());

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nomal:
                sendNomalNotification();
                break;
            case R.id.tv_fold:
                sendFoldNotification();
                break;

            case R.id.tv_hang:
                sendHangNotification();

            case R.id.tv_bigpic:
                sendBigPicNotification();
                break;
            case R.id.tv_bigtext:
                sendBigTextNotification();
                break;
            case R.id.tv_biginbox:
                sendInboxNotification();
                break;
        }
    }
}
