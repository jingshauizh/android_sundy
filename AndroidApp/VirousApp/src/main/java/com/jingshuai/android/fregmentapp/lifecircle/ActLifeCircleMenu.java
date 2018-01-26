package com.jingshuai.android.fregmentapp.lifecircle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.android.fregmentapp.bitmap.ActBitMapLoad;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ActLifeCircleMenu extends AppCompatActivity {
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_life_circle_menu);
        mUnbinder =  ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_lifecircle_common)
    public void commonLoadBitMap(){
        MLog.i("commonLoadBitMap");
        Intent intent = new Intent(getApplicationContext(), ActLifeFregment.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}

