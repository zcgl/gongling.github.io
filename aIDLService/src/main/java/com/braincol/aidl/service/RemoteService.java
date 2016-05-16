package com.braincol.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.RemoteViews;

import com.braincol.aidl.client.forActivity;

public class RemoteService extends Service {
	private final static String TAG = "RemoteService";
	private forActivity callback;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate called.");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand called.");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.i(TAG, "onStart called.");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "OnBind");
		return new MyBinder();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind called.");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy called.");
	}


	private class MyBinder extends RemoteBeauty.Stub{
		@Override
		public void registerTestCall(forActivity cb) throws RemoteException
		{
			Log.i(TAG, "registerTestCall called.");
			callback = cb;

		}
		@Override
		public String getCurrentPageUrl() throws RemoteException{
			Log.i(TAG, "getCurrentPageUrl called.");
			return "http://www.cnblogs.com/hibraincol/";
		}
		@Override
		public String getAllInfo() throws RemoteException{
			Log.i(TAG, "getAllInfo called.");
			callback.performAction("service toast");

			return "姓名:feifei 年龄:21 大小:female";
		}
		@Override
		public Beauty getBeauty() throws RemoteException {
			Log.i(TAG, "getBeauty called.");
			Beauty beauty = new Beauty();
			beauty.setName("feifei");
			beauty.setAge(21);
			beauty.setSex("female");
			return beauty;
		}
	}


}

