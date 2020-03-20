package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.Item1.Item1;
import com.lenovo.manufacture.Item2.Item2;
import com.lenovo.manufacture.Item3.Item3;
import com.lenovo.manufacture.Item4.Item4;
import com.lenovo.manufacture.Item5.Item5;
import com.lenovo.manufacture.Item6.Item6;
import com.lenovo.manufacture.Item7.Item7;
import com.lenovo.manufacture.Item8.Item8;
import com.lenovo.manufacture.Item9.Item9;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {

        TWebView webView = new TWebView(this, null);
        ViewGroup viewParent = findViewById(R.id.webView1);
        viewParent.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /* mWebView.showLog("test Log"); */
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setBackgroundColor(0);
        webView.requestFocus();
        webView.addJavascriptInterface(new JavaScriptInterface(this), "nativeMethod");
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);


        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSetting.setDefaultZoom(zoomDensity);
    }



    public class JavaScriptInterface {
        Activity mActivity;

        JavaScriptInterface(Activity mActivity) {
            this.mActivity = mActivity;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startActivity(int a) {
            switch (a){
                case 1:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item1.class));
                    break;
                case 2:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item2.class));
                    break;
                case 3:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item3.class));
                    break;
                case 4:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item4.class));
                    break;
                case 5:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item5.class));
                    break;
                case 6:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item6.class));
                    break;
                case 7:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item7.class));
                    break;
                case 8:
                    mActivity.startActivity(new Intent().setClass(MainActivity.this, Item9.class));
                    break;
            }
        }
    }

}
