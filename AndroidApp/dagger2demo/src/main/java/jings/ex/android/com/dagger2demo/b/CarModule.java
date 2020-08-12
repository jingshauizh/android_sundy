package jings.ex.android.com.dagger2demo.b;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jings on 2020/8/10.
 */
@Module
public class CarModule {
    @Provides
    public Engine providerEngine(){
        return new Engine("DAzhong");
    }
}
