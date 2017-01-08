package com.example.gongling.myapplication.wxapi;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017-01-08.
 */
public class WXApi {
    public static final String APP_ID = "wxbdc5610cc59c1631";
    //public static final String APP_KEY = "2f5a245cee506d45f75e1f47505de86b";

    public static IWXAPI wXApi;
    public static IWXAPI getIWXAPI(Context context) {
        if (wXApi == null) {
            wXApi = WXAPIFactory.createWXAPI(context, APP_ID);
            wXApi.registerApp(APP_ID);
        }
        return wXApi;
    }

    public static SendAuth.Req getLoginAPIParams() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "none";
        return req;
    }
}
