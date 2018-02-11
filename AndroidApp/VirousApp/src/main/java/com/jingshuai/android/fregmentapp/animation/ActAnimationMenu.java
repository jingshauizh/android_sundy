package com.jingshuai.android.fregmentapp.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.jsnative.ActHackJs;
import com.jingshuai.android.fregmentapp.jsnative.ActJsApi;
import com.jingshuai.android.fregmentapp.jsnative.ActJsUrl;
import com.jingshuai.android.fregmentapp.jsnative.ButterKnifeActivity;
import com.jingshuai.android.fregmentapp.lifecircle.ActLifeFregment;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.OnClick;

public class ActAnimationMenu extends ButterKnifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_animation);
        super.onCreate(savedInstanceState);

    }


    @OnClick(R.id.btn_anima_common)
    public void commonLoadBitMap(){
        MLog.i("commonLoadBitMap");
        Intent intent = new Intent(getApplicationContext(), ActAnimaAttribute.class);
        startActivity(intent);
    }

    ///btn_JS_URL
    @OnClick(R.id.btn_anima_URL)
    public void btnOpenJsURL(){
        MLog.i("btnOpenJsURL");
        Intent intent = new Intent(getApplicationContext(), ActAnimaAttribute.class);
        startActivity(intent);
    }

    ///btn_JS_URL
    @OnClick(R.id.btn_anima_API)
    public void btnHackJsAPI(){
        MLog.i("btnOpenJsAPI");
        Intent intent = new Intent(getApplicationContext(), ActAnimaAttribute.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        //mUnbinder.unbind();
        super.onDestroy();
    }
}
