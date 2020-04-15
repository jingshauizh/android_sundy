package com.jingshuai.android.fregmentapp.test.okhttp;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.activity.ActivityLibBase;
import com.jingshuai.appcommonlib.log.MLog;


import java.io.IOException;

public class OkHttpActivity extends ActivityLibBase {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.act_ok_http);
    }

    @Override
    public void initViews() {
        mButton = (Button)findViewById(R.id.btn_OkHttp);
    }

    @Override
    public void initListeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建okHttpClient对象
                //OkHttpClient mOkHttpClient = new OkHttpClient();
                ////创建一个Request
                //final Request request = new Request.Builder()
                //        .url("https://github.com/hongyangAndroid")
                //        .build();
                ////new call
                //Call call = mOkHttpClient.newCall(request);
                ////请求加入调度
                //call.enqueue(new Callback()
                //{
                //    @Override
                //    public void onFailure(Request request, IOException e)
                //    {
                //    }
                //
                //    @Override
                //    public void onResponse(final Response response) throws IOException
                //    {
                //        String htmlStr =  response.body().string();
                //        MLog.i(htmlStr);
                //    }
                //});
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected boolean cancelRequest() {
        return false;
    }
}
