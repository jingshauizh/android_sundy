package com.jingshuai.android.fregmentapp.jsnative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;



/*****
 *
 * TODO  https://www.jianshu.com/p/a6f7b391a0b8
 *  ????
 *
 *
 * **/
public class ActHackJs extends ButterKnifeActivity {

    @BindView(R.id.v_mwebview)
    WebView mWebView;
    @BindView(R.id.v_web_button_load)
    Button mBtnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_act_js_url);
        super.onCreate(savedInstanceState);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(this, "jsInterface");//??js??test.xxx
        mWebView.loadUrl("file:///android_asset/hack.html");
    }

    @JavascriptInterface
    public void onButtonClick(String msg){//??js?xxx.hello("")
        MLog.i("hello");
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void onImageClick(String msg,int width, int height){//??js?xxx.hello("")
        MLog.i("msg ="+msg);
        MLog.i("width="+width);
        MLog.i("height="+height);

    }


}
