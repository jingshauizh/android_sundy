package com.mvp.jingshuai.android_iod.di.module;

import dagger.internal.Factory;
import de.greenrobot.event.EventBus;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_EventBusFactory implements Factory<EventBus> {
  private final ApplicationModule module;

  public ApplicationModule_EventBusFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public EventBus get() {  
    EventBus provided = module.eventBus();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<EventBus> create(ApplicationModule module) {  
    return new ApplicationModule_EventBusFactory(module);
  }
}

