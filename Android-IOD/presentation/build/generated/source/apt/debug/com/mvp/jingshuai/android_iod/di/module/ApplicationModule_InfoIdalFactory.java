package com.mvp.jingshuai.android_iod.di.module;

import android.database.sqlite.SQLiteDatabase;
import com.mvp.jingshuai.data.idal.InfoIdal;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApplicationModule_InfoIdalFactory implements Factory<InfoIdal> {
  private final ApplicationModule module;
  private final Provider<SQLiteDatabase> databaseProvider;

  public ApplicationModule_InfoIdalFactory(ApplicationModule module, Provider<SQLiteDatabase> databaseProvider) {  
    assert module != null;
    this.module = module;
    assert databaseProvider != null;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public InfoIdal get() {  
    InfoIdal provided = module.infoIdal(databaseProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<InfoIdal> create(ApplicationModule module, Provider<SQLiteDatabase> databaseProvider) {  
    return new ApplicationModule_InfoIdalFactory(module, databaseProvider);
  }
}

