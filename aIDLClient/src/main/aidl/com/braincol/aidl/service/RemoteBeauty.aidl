package com.braincol.aidl.service;
import com.braincol.aidl.service.Beauty;
import com.braincol.aidl.client.forActivity;
 interface RemoteBeauty {
	String getAllInfo(); 
	Beauty getBeauty();
	String getCurrentPageUrl();
	void registerTestCall(forActivity a);
}
