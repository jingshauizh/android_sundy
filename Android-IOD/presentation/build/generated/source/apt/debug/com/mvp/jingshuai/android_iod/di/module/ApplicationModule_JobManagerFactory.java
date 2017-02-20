package com.mvp.jingshuai.android_iod.di.module;

import com.path.android.jobqueue.JobManager;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_JobManagerFactory implements Factory<JobManager> {
  private final ApplicationModule module;

  public ApplicationModule_JobManagerFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public JobManager get() {  
    JobManager provided = module.jobManager();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<JobManager> create(ApplicationModule module) {  
    return new ApplicationModule_JobManagerFactory(module);
  }
}

