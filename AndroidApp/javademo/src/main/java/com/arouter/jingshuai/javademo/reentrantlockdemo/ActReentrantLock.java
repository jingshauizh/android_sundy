package com.arouter.jingshuai.javademo.reentrantlockdemo;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arouter.jingshuai.javademo.ButterKnifeActivity;
import com.arouter.jingshuai.javademo.R;

import butterknife.OnClick;


@Route(path = "/thread/act_reentrantlock", group = "thread")
public class ActReentrantLock extends ButterKnifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_reentrant_lock);
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.btn_reentrantlock_1)
    public void onBtnClick(View v){
        Depot mDepot = new Depot();
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
