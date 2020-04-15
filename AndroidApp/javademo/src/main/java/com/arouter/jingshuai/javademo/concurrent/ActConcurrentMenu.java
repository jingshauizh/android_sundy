package com.arouter.jingshuai.javademo.concurrent;


import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.arouter.jingshuai.javademo.ButterKnifeActivity;
import com.arouter.jingshuai.javademo.R;

import butterknife.OnClick;

public class ActConcurrentMenu extends ButterKnifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_java_main);
        super.onCreate(savedInstanceState);

    }


    @OnClick(R.id.btn_dialog_1)
    public void onBtnClick(View v){
        ARouter.getInstance().build("/thread/act_reentrantlock","thread").navigation();
    }
    @OnClick(R.id.btn_dialog_2)
    public void onBtnClickOpen(View v){
        ARouter.getInstance().build("/thread/act_reentrantlock_demo2","thread").navigation();

    }

    @OnClick(R.id.btn_dialog_3)
    public void onBtnClickOpenThread(View v){
        ARouter.getInstance().build("/thread/act_reentrantlock_demo3","thread").navigation();
    }
}
