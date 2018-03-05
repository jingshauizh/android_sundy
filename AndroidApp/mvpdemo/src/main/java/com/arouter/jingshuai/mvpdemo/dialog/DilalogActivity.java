package com.arouter.jingshuai.mvpdemo.dialog;




import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.arouter.jingshuai.mvpdemo.ButterKnifeActivity;
import com.arouter.jingshuai.mvpdemo.R;
import com.arouter.jingshuai.mvpdemo.newa.IContract;
import com.arouter.jingshuai.mvpdemo.newa.NewPresenter;
import com.jingshuai.appcommonlib.log.MLog;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = "/module/1", group = "mvp")
public class DilalogActivity extends ButterKnifeActivity implements  IContract.IView{


    @BindView(R.id.btn_dialog_1)
    Button btn_Next;
    @BindView(R.id.tv_dialog_newstext)
    TextView tv_newsText;

    private NewPresenter mNewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dilalog);
        super.onCreate(savedInstanceState);
        MLog.i("onCreate");

    }

    @OnClick(R.id.btn_dialog_1)
    public void onBtnClick(View v){
        showNormalDialog();
    }
    @OnClick(R.id.btn_dialog_2)
    public void onBtnClickOpen(View v){
        ARouter.getInstance().build("/module/2","mvp").navigation();
    }

    @OnClick(R.id.btn_dialog_3)
    public void onBtnClickOpenThread(View v){
        ARouter.getInstance().build("/module/thread","thread").navigation();
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DilalogActivity.this);
       // normalDialog.setIcon(R.drawable.ic_launcher_foreground);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        MLog.i("确定");
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        MLog.i("关闭");
                    }
                });
        // 显示
        normalDialog.show();
    }


    @Override
    public void updateNews(String newsText) {
        tv_newsText.setText(newsText);
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
