package com.mvp.jingshuai.android_iod.di.component;

import android.content.Context;

import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailActivity;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailPresenter;
import com.mvp.jingshuai.android_iod.api.ApiModule;
import com.mvp.jingshuai.android_iod.api.ApiService;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule;
import com.mvp.jingshuai.android_iod.job.Info.FetchFeedJob;
import com.mvp.jingshuai.android_iod.job.Info.FetchInfoJob;
import com.path.android.jobqueue.JobManager;

import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;

/**
 * Created by eqruvvz on 1/19/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {
    ApiService apiService();
    EventBus eventBus();
    DemoConfig demoConfig();
    Context appContext();
    JobManager jobManager();

    //定义注入的方法
    void inject(InfoODetailActivity activity);
    void inject(FetchFeedJob fetchFeedJob);
    void inject(FetchInfoJob fetchInfoJob);
    void inject(InfoODetailPresenter infoODetailPresenter);

}
