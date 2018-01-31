package com.jingshuai.android.fregmentapp.lifecircle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.bitmap.ActBitMapLoad;
import com.jingshuai.android.fregmentapp.jsnative.ActJsApi;
import com.jingshuai.android.fregmentapp.jsnative.ActJsUrl;
import com.jingshuai.android.fregmentapp.jsnative.ButterKnifeActivity;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ActLifeCircleMenu extends ButterKnifeActivity {
    //private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.act_life_circle_menu);
        super.onCreate(savedInstanceState);
        //mUnbinder =  ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_lifecircle_common)
    public void commonLoadBitMap(){
        MLog.i("commonLoadBitMap");
        Intent intent = new Intent(getApplicationContext(), ActLifeFregment.class);
        startActivity(intent);
    }

    ///btn_JS_URL
    @OnClick(R.id.btn_JS_URL)
    public void btnOpenJsURL(){
        MLog.i("btnOpenJsURL");
        Intent intent = new Intent(getApplicationContext(), ActJsUrl.class);
        startActivity(intent);
    }

    ///btn_JS_URL
    @OnClick(R.id.btn_JS_API)
    public void btnOpenJsAPI(){
        MLog.i("btnOpenJsAPI");
        Intent intent = new Intent(getApplicationContext(), ActJsApi.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        //mUnbinder.unbind();
        super.onDestroy();
    }
}

