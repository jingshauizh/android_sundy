package com.mvp.jingshuai.android_iod.job.Info;

import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.network.ApiService;
import dagger.MembersInjector;
import de.greenrobot.event.EventBus;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class FetchInfoJob_MembersInjector implements MembersInjector<FetchInfoJob> {
  private final MembersInjector<BaseJob> supertypeInjector;
  private final Provider<ApiService> mApiServiceProvider;
  private final Provider<EventBus> mEventBusProvider;
  private final Provider<InfoIdal> mInfoIdalProvider;

  public FetchInfoJob_MembersInjector(MembersInjector<BaseJob> supertypeInjector, Provider<ApiService> mApiServiceProvider, Provider<EventBus> mEventBusProvider, Provider<InfoIdal> mInfoIdalProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mApiServiceProvider != null;
    this.mApiServiceProvider = mApiServiceProvider;
    assert mEventBusProvider != null;
    this.mEventBusProvider = mEventBusProvider;
    assert mInfoIdalProvider != null;
    this.mInfoIdalProvider = mInfoIdalProvider;
  }

  @Override
  public void injectMembers(FetchInfoJob instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mApiService = mApiServiceProvider.get();
    instance.mEventBus = mEventBusProvider.get();
    instance.mInfoIdal = mInfoIdalProvider.get();
  }

  public static MembersInjector<FetchInfoJob> create(MembersInjector<BaseJob> supertypeInjector, Provider<ApiService> mApiServiceProvider, Provider<EventBus> mEventBusProvider, Provider<InfoIdal> mInfoIdalProvider) {  
      return new FetchInfoJob_MembersInjector(supertypeInjector, mApiServiceProvider, mEventBusProvider, mInfoIdalProvider);
  }
}

