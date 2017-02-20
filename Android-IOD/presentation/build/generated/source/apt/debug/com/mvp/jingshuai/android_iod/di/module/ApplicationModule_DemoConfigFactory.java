package com.mvp.jingshuai.android_iod.di.module;

import com.mvp.jingshuai.android_iod.config.DemoConfig;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_DemoConfigFactory implements Factory<DemoConfig> {
  private final ApplicationModule module;

  public ApplicationModule_DemoConfigFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public DemoConfig get() {  
    DemoConfig provided = module.demoConfig();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<DemoConfig> create(ApplicationModule module) {  
    return new ApplicationModule_DemoConfigFactory(module);
  }
}

