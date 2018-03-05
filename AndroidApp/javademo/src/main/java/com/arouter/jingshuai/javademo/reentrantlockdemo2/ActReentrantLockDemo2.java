package com.arouter.jingshuai.javademo.reentrantlockdemo2;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arouter.jingshuai.javademo.ButterKnifeActivity;
import com.arouter.jingshuai.javademo.R;

import butterknife.OnClick;


@Route(path = "/thread/act_reentrantlock_demo2", group = "thread")
public class ActReentrantLockDemo2 extends ButterKnifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_reentrant_lock);
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.btn_reentrantlock_1)
    public void onBtnClick(View v){
        Depot2 mDepot = new Depot2();
        Producer2 mPro = new Producer2(mDepot);
        Customer2 mCus = new Customer2(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
