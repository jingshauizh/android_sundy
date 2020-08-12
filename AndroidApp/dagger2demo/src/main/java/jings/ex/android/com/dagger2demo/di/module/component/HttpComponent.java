package jings.ex.android.com.dagger2demo.di.module.component;

import dagger.Component;
import jings.ex.android.com.dagger2demo.MainActivity;
import jings.ex.android.com.dagger2demo.di.module.HttpModule;
import jings.ex.android.com.dagger2demo.scope.UserScope;

/**
 * Created by jings on 2020/8/7.
 */

@Component(modules = { HttpModule.class})
public interface HttpComponent {
    void inject(MainActivity activity);
}
