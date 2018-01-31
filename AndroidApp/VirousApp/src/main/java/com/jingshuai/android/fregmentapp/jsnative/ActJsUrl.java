package com.jingshuai.android.fregmentapp.jsnative;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class ActJsUrl extends ButterKnifeActivity {

    @BindView(R.id.v_mwebview)
    WebView mWebView;
    @BindView(R.id.v_web_button_load)
    Button mBtnLoad;

    private WebViewClient mWebViewClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_js_url);
        super.onCreate(savedInstanceState);
        //initWebViewClient();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // ????'jsobj'???????`jsobj.say(...)`??
//        mWebView.addJavascriptInterface(new JSObject(), "jsobj");
//        mWebView.setWebViewClient(mWebClient);
//        mWebView.loadUrl("tel:100861");

        mWebView.addJavascriptInterface(this, "test");//??js??test.xxx
        mWebView.loadUrl("file:///android_asset/test.html");
    }


    @JavascriptInterface
    public void hello(String msg){//??js?xxx.hello("")
        MLog.i("hello");
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.v_web_button_load)
    public void btnClicked(View v){
        mWebView.loadUrl("javascript:jsobj.say('hello')");
    }

    public class JSObject {
        @JavascriptInterface
        public void say(String words) {
            // todo
            MLog.i("JSObject JavascriptInterface say:"+words);
        }
    }



//    private void initWebViewClient(){
//        mWebViewClient = new WebViewClient() {
//            //??shouldOverrideUrlLoading ??
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                MLog.i("onPageFinished url="+url);
//            }
//
//        };
//    }





    protected WebViewClient mWebClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // ??tel????
            if (url.startsWith("tel:")) {
                MLog.i("shouldOverrideUrlLoading url="+url);
               // Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            // ??mp4????
            if (url.endsWith(".mp4")) {
                //viewVideo(url);
                return true;
            }

            // ????????
            if (Pattern.compile("/media/image").matcher(url).find()) {
                //viewImage(url);
                return true;
            }
            // ??webview
            if (Pattern.compile("/webapp/close/webview").matcher(url).find()) {
                onBackPressed();
                return true;
            }

            // ??????url???
            if (Pattern.compile("/webapp/patient_card/").matcher(url).find()) {
                Uri uri = Uri.parse(url);
                String patientId = uri.getQueryParameter("patient_id");
                //viewPatientInfo(patientId);

                return true;
            }

            return super.shouldOverrideUrlLoading(view, url);
        }
    };
}
