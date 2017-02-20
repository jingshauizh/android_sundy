package com.mvp.jingshuai.android_iod.di.module;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_AppContextFactory implements Factory<Context> {
  private final ApplicationModule module;

  public ApplicationModule_AppContextFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Context get() {  
    Context provided = module.appContext();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Context> create(ApplicationModule module) {  
    return new ApplicationModule_AppContextFactory(module);
  }
}

