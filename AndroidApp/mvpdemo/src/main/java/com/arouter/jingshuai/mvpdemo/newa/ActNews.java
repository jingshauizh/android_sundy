package com.arouter.jingshuai.mvpdemo.newa;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.arouter.jingshuai.mvpdemo.ButterKnifeActivity;
import com.arouter.jingshuai.mvpdemo.R;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = "/module/2", group = "mvp")
public class ActNews extends ButterKnifeActivity implements  IContract.IView{


    @BindView(R.id.btn_news_getnext)
    Button btn_Next;
    @BindView(R.id.tv_news_newstext)
    TextView tv_newsText;

    private NewPresenter mNewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_act_news);
        super.onCreate(savedInstanceState);
        MLog.i("onCreate");

    }


    @Override
    public void updateNews(String newsText) {
        tv_newsText.setText(newsText);
    }

    @OnClick(R.id.btn_news_getnext)
    public void onBtnClick(View v){

        mNewPresenter.getNextNews();
    }

    @Override
    public void setPresenter(NewPresenter pNewPresenter) {
        mNewPresenter = pNewPresenter;
    }
    @Override
    protected void onStart() {
        super.onStart();
        MLog.i("onStart");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        MLog.i("onRestart");
    }

    @Override
    protected void onResume() {
        if(mNewPresenter== null){
            mNewPresenter = new NewPresenter(this);
        }
        super.onResume();
        MLog.i("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MLog.i("onPause");
    }

    @Override
    protected void onStop() {
        mNewPresenter= null;
        super.onStop();
        MLog.i("onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MLog.i("onSaveInstanceState");
        outState.putInt("data",111222);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MLog.i("onRestoreInstanceState");
        MLog.i("onRestoreInstanceState savedInstanceState="+savedInstanceState.get("data"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MLog.i("onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        MLog.i("onDestroy");
    }
}
