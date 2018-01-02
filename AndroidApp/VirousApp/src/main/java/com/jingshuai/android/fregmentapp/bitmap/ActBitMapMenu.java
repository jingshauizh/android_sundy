package com.jingshuai.android.fregmentapp.bitmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ActBitMapMenu extends AppCompatActivity {

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bit_map_menu);
        mUnbinder =  ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_bitmap_common)
    public void commonLoadBitMap(){
        MLog.i("commonLoadBitMap");
        Intent intent = new Intent(getApplicationContext(), ActBitMapLoad.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
