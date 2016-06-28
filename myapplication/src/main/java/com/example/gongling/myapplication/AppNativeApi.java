package com.example.gongling.myapplication;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class AppNativeApi implements Serializable {

    private final Activity mParent;

    public AppNativeApi(Activity parent) {
        this.mParent = parent;
    }

    /**
     * 弹出Toast提示
     *
     * @param msg ;Toast 提示内容
     */
    private void showToast(String msg) {
        Toast.makeText(mParent, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 内嵌html5跳转到native模块,native模块的启动模式请在mainfest里设置
     *
     * @param url   ;native模块url地址,ex:yhd://home/,yhd://cart/
     * @param param ;json格式参数:如:{"from":"h5","pmid":"123456"},具体请看无限规范文档
     */
    @JavascriptInterface
    public void gotToNative(String url, String param) {
        HashMap<String, String> mapParam = null;
        if (!TextUtils.isEmpty(param)) {
            mapParam = new HashMap<String, String>();
            try {
                JSONObject tparam = new JSONObject(param);
                Iterator<?> objkey = tparam.keys();
                while (objkey.hasNext()) {
                    String jkey = objkey.next().toString();
                    mapParam.put(jkey, tparam.getString(jkey));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                showToast("模块参数解析错误");
            }
        }
       
        String from = null;
        if (mapParam != null) {
            from = mapParam.get("from");
        }

    }










}
