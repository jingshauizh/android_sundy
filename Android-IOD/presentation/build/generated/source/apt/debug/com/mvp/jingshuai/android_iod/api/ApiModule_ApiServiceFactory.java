package com.mvp.jingshuai.android_iod.api;

import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.data.network.ApiService;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_ApiServiceFactory implements Factory<ApiService> {
  private final ApiModule module;
  private final Provider<DemoConfig> demoConfigProvider;

  public ApiModule_ApiServiceFactory(ApiModule module, Provider<DemoConfig> demoConfigProvider) {  
    assert module != null;
    this.module = module;
    assert demoConfigProvider != null;
    this.demoConfigProvider = demoConfigProvider;
  }

  @Override
  public ApiService get() {  
    ApiService provided = module.apiService(demoConfigProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ApiService> create(ApiModule module, Provider<DemoConfig> demoConfigProvider) {  
    return new ApiModule_ApiServiceFactory(module, demoConfigProvider);
  }
}

