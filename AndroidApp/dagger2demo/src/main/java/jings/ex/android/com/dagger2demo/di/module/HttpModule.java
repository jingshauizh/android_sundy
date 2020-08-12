package jings.ex.android.com.dagger2demo.di.module;

import dagger.Module;
import dagger.Provides;
import jings.ex.android.com.dagger2demo.entity.HttpModuleObj;
import jings.ex.android.com.dagger2demo.scope.UserScope;

/**
 * Created by jings on 2020/8/7.
 */
@Module
public class HttpModule {
    @Provides
    public HttpModuleObj gethttpModuleObj(){
        return new HttpModuleObj();
    }
}
