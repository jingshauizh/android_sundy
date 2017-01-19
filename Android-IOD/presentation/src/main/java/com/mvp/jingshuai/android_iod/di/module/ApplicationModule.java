package com.mvp.jingshuai.android_iod.di.module;

import android.content.Context;

import com.mvp.jingshuai.android_iod.IODApplication;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by eqruvvz on 1/19/2017.
 */

@Module
public class ApplicationModule {
    private final IODApplication mApp;
    public ApplicationModule(IODApplication app){
        mApp = app;
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public DemoConfig demoConfig() {
        return new DemoConfig(mApp);
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public JobManager jobManager() {
        Configuration config = new Configuration.Builder(mApp)
                .consumerKeepAlive(45)
                .maxConsumerCount(3)
                .minConsumerCount(1)
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        if (job instanceof BaseJob) {
                            ((BaseJob) job).inject(mApp.getAppComponent());
                        }
                    }
                })
                .build();
        return new JobManager(mApp, config);
    }




}
