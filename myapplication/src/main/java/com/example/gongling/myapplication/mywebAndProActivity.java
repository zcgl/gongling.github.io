package com.example.gongling.myapplication;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by gongling on 2016/6/7.
 */
public class mywebAndProActivity extends Activity {
   private WebView mProductWebView;
    private TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myweb_scrolling);

        mProductWebView=(WebView)findViewById(R.id.myweb);
        text=(TextView)findViewById(R.id.myweb_bottom_text);

        WebSettings settings = mProductWebView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        //Lg.e("======+",width,750,width*100/750);
        mProductWebView.setInitialScale(width * 100 / 750 - 4);
        if (Build.VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getCacheDir().getPath());
        settings.setUseWideViewPort(true);// 影响默认满屏和双击缩放
        settings.setLoadWithOverviewMode(true);// 影响默认满屏和手势缩放
        settings.setBlockNetworkImage(true);
        mProductWebView.addJavascriptInterface(new AppNativeApi(this), "yhd");

        mProductWebView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return true;
                }
        });
        String dataStr=getResources().getString(R.string.large_text);


        mProductWebView.loadDataWithBaseURL("", dataStr, "text/html", "utf-8", "");

    }
}
