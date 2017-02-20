package com.mvp.jingshuai.android_iod.di.module;

import android.database.sqlite.SQLiteDatabase;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_DatabaseFactory implements Factory<SQLiteDatabase> {
  private final ApplicationModule module;

  public ApplicationModule_DatabaseFactory(ApplicationModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public SQLiteDatabase get() {  
    SQLiteDatabase provided = module.database();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<SQLiteDatabase> create(ApplicationModule module) {  
    return new ApplicationModule_DatabaseFactory(module);
  }
}

