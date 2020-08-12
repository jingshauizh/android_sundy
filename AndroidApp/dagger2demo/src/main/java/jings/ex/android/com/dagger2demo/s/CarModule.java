package jings.ex.android.com.dagger2demo.s;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import jings.ex.android.com.dagger2demo.scope.UserScope;

/**
 * Created by jings on 2020/8/10.
 */
@UserScope
@Module
public class CarModule {
    @UserScope
    @Provides
    public Engine providerEngineA(){
        return new Engine("DAzhong engineA");
    }


    @Named("engineB")
    @Provides
    public Engine providerEngineB(){
        return new Engine("DAzhong engineB");
    }
}
