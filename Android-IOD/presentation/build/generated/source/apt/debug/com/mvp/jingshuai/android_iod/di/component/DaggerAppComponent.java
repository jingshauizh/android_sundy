package com.mvp.jingshuai.android_iod.di.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailActivity;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailPresenter;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailPresenter_MembersInjector;
import com.mvp.jingshuai.android_iod.api.ApiModule;
import com.mvp.jingshuai.android_iod.api.ApiModule_ApiServiceFactory;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_AppContextFactory;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_DatabaseFactory;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_DemoConfigFactory;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_EventBusFactory;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_InfoIdalFactory;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule_JobManagerFactory;
import com.mvp.jingshuai.android_iod.job.Info.FetchInfoJob;
import com.mvp.jingshuai.android_iod.job.Info.FetchInfoJob_MembersInjector;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.network.ApiService;
import com.path.android.jobqueue.JobManager;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import de.greenrobot.event.EventBus;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<DemoConfig> demoConfigProvider;
  private Provider<ApiService> apiServiceProvider;
  private Provider<SQLiteDatabase> databaseProvider;
  private Provider<InfoIdal> infoIdalProvider;
  private Provider<EventBus> eventBusProvider;
  private Provider<Context> appContextProvider;
  private Provider<JobManager> jobManagerProvider;
  private MembersInjector<FetchInfoJob> fetchInfoJobMembersInjector;
  private MembersInjector<InfoODetailPresenter> infoODetailPresenterMembersInjector;

  private DaggerAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.demoConfigProvider = ScopedProvider.create(ApplicationModule_DemoConfigFactory.create(builder.applicationModule));
    this.apiServiceProvider = ScopedProvider.create(ApiModule_ApiServiceFactory.create(builder.apiModule, demoConfigProvider));
    this.databaseProvider = ScopedProvider.create(ApplicationModule_DatabaseFactory.create(builder.applicationModule));
    this.infoIdalProvider = ScopedProvider.create(ApplicationModule_InfoIdalFactory.create(builder.applicationModule, databaseProvider));
    this.eventBusProvider = ScopedProvider.create(ApplicationModule_EventBusFactory.create(builder.applicationModule));
    this.appContextProvider = ScopedProvider.create(ApplicationModule_AppContextFactory.create(builder.applicationModule));
    this.jobManagerProvider = ScopedProvider.create(ApplicationModule_JobManagerFactory.create(builder.applicationModule));
    this.fetchInfoJobMembersInjector = FetchInfoJob_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), apiServiceProvider, eventBusProvider, infoIdalProvider);
    this.infoODetailPresenterMembersInjector = InfoODetailPresenter_MembersInjector.create(jobManagerProvider, appContextProvider, eventBusProvider, infoIdalProvider);
  }

  @Override
  public ApiService apiService() {  
    return apiServiceProvider.get();
  }

  @Override
  public InfoIdal infoIdal() {  
    return infoIdalProvider.get();
  }

  @Override
  public EventBus eventBus() {  
    return eventBusProvider.get();
  }

  @Override
  public DemoConfig demoConfig() {  
    return demoConfigProvider.get();
  }

  @Override
  public Context appContext() {  
    return appContextProvider.get();
  }

  @Override
  public SQLiteDatabase database() {  
    return databaseProvider.get();
  }

  @Override
  public JobManager jobManager() {  
    return jobManagerProvider.get();
  }

  @Override
  public void inject(InfoODetailActivity activity) {  
    MembersInjectors.noOp().injectMembers(activity);
  }

  @Override
  public void inject(FetchInfoJob fetchInfoJob) {  
    fetchInfoJobMembersInjector.injectMembers(fetchInfoJob);
  }

  @Override
  public void inject(InfoODetailPresenter infoODetailPresenter) {  
    infoODetailPresenterMembersInjector.injectMembers(infoODetailPresenter);
  }

  public static final class Builder {
    private ApplicationModule applicationModule;
    private ApiModule apiModule;
  
    private Builder() {  
    }
  
    public AppComponent build() {  
      if (applicationModule == null) {
        throw new IllegalStateException("applicationModule must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      return new DaggerAppComponent(this);
    }
  
    public Builder applicationModule(ApplicationModule applicationModule) {  
      if (applicationModule == null) {
        throw new NullPointerException("applicationModule");
      }
      this.applicationModule = applicationModule;
      return this;
    }
  
    public Builder apiModule(ApiModule apiModule) {  
      if (apiModule == null) {
        throw new NullPointerException("apiModule");
      }
      this.apiModule = apiModule;
      return this;
    }
  }
}

