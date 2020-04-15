package com.arouter.jingshuai.javademo.concurrent.countdownlatch.actcountdownlatch;

import android.content.Context;
import com.arouter.jingshuai.javademo.mvp.BasePresenter;
import com.arouter.jingshuai.javademo.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ActCountdownlatchContract {
    interface View extends BaseView {
        void showMessage(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void startRun();
        void sendMore();
    }
}
