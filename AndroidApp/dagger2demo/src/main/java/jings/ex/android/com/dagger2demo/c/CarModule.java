package jings.ex.android.com.dagger2demo.c;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by jings on 2020/8/10.
 */
@Module
public class CarModule {
    @Named("engineA")
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
