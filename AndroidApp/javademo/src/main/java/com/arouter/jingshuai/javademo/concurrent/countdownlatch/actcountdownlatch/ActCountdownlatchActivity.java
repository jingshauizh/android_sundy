package com.arouter.jingshuai.javademo.concurrent.countdownlatch.actcountdownlatch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arouter.jingshuai.javademo.R;
import com.arouter.jingshuai.javademo.mvp.MVPBaseActivity;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */
@Route(path = "/thread/act_countdownlatch", group = "thread")
public class ActCountdownlatchActivity extends MVPBaseActivity<ActCountdownlatchContract.View, ActCountdownlatchPresenter> implements ActCountdownlatchContract.View {

    @BindView(R.id.tv_message_text)
    TextView mMsgView;
    @BindView(R.id.btn_start)
    Button mBtnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_countdownlatch);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showMessage(final String msg) {
        MLog.i("showMessage msg"+msg);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mMsgView.append(msg);
//            }
//        });
        mMsgView.append(msg);

    }

    @OnClick(R.id.btn_start)
    public void start(View v){
        MLog.i("btn_start start run");
        this.mPresenter.startRun();
    }

    @OnClick(R.id.btn_send_more)
    public void sendMore(View v){
        MLog.i("sendMore start run");
        this.mPresenter.sendMore();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

}
