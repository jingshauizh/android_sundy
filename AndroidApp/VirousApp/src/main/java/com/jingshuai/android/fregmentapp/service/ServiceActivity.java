package com.jingshuai.android.fregmentapp.service;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingshuai.android.fregmentapp.R;
import com.jingshuai.appcommonlib.activity.ActivityLibBase;

public class ServiceActivity extends ActivityLibBase {

    private Button mBtnService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void setContentView() {
        setContentView(R.layout.act_service);
    }

    @Override
    public void initViews() {
        mBtnService = (Button)findViewById(R.id.btn_service_activity_start_service);
    }

    @Override
    public void initListeners() {
        mBtnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntentService.startActionFoo(ServiceActivity.this,"param1","param2");
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
