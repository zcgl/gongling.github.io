package me.leolin.shortcutbadger.impl;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;
import me.leolin.shortcutbadger.util.BroadcastHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author leolin
 */
public class XiaomiHomeBadger implements Badger {

    public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
    public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
    public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        try {
           // Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
            //Object miuiNotification = miuiNotificationClass.newInstance();
            //Field field = miuiNotification.getClass().getDeclaredField("messageCount");
            // field.setAccessible(true);
            //field.set(miuiNotification, String.valueOf(badgeCount == 0 ? "" : badgeCount));
            //小米MIUI6.0以及以上用此显示角标
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentTitle("title")
                    .setContentText("text")
                    .setSmallIcon(android.R.drawable.sym_def_app_icon);
            Notification notification = builder.build();

            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, badgeCount);

            mNotificationManager.notify(0, notification);
        } catch (Exception e) {
            Intent localIntent = new Intent(
                    INTENT_ACTION);
            localIntent.putExtra(EXTRA_UPDATE_APP_COMPONENT_NAME, componentName.getPackageName() + "/" + componentName.getClassName());
            localIntent.putExtra(EXTRA_UPDATE_APP_MSG_TEXT, String.valueOf(badgeCount == 0 ? "" : badgeCount));
            if(BroadcastHelper.canResolveBroadcast(context, localIntent)) {
                context.sendBroadcast(localIntent);
            } else {
                throw new ShortcutBadgeException("unable to resolve intent: " + localIntent.toString());
            }
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.miui.miuilite",
                "com.miui.home",
                "com.miui.miuihome",
                "com.miui.miuihome2",
                "com.miui.mihome",
                "com.miui.mihome2"
        );
    }
}
