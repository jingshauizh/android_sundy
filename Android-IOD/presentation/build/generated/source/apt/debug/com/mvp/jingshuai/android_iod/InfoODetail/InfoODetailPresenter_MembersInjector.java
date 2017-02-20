package com.mvp.jingshuai.android_iod.InfoODetail;

import android.content.Context;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.path.android.jobqueue.JobManager;
import dagger.MembersInjector;
import de.greenrobot.event.EventBus;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class InfoODetailPresenter_MembersInjector implements MembersInjector<InfoODetailPresenter> {
  private final Provider<JobManager> mJobManagerProvider;
  private final Provider<Context> mAppContextProvider;
  private final Provider<EventBus> mEventBusProvider;
  private final Provider<InfoIdal> mInfoIdalProvider;

  public InfoODetailPresenter_MembersInjector(Provider<JobManager> mJobManagerProvider, Provider<Context> mAppContextProvider, Provider<EventBus> mEventBusProvider, Provider<InfoIdal> mInfoIdalProvider) {  
    assert mJobManagerProvider != null;
    this.mJobManagerProvider = mJobManagerProvider;
    assert mAppContextProvider != null;
    this.mAppContextProvider = mAppContextProvider;
    assert mEventBusProvider != null;
    this.mEventBusProvider = mEventBusProvider;
    assert mInfoIdalProvider != null;
    this.mInfoIdalProvider = mInfoIdalProvider;
  }

  @Override
  public void injectMembers(InfoODetailPresenter instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mJobManager = mJobManagerProvider.get();
    instance.mAppContext = mAppContextProvider.get();
    instance.mEventBus = mEventBusProvider.get();
    instance.mInfoIdal = mInfoIdalProvider.get();
  }

  public static MembersInjector<InfoODetailPresenter> create(Provider<JobManager> mJobManagerProvider, Provider<Context> mAppContextProvider, Provider<EventBus> mEventBusProvider, Provider<InfoIdal> mInfoIdalProvider) {  
      return new InfoODetailPresenter_MembersInjector(mJobManagerProvider, mAppContextProvider, mEventBusProvider, mInfoIdalProvider);
  }
}

