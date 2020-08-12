package jings.ex.android.com.dagger2demo.di.module.component;

import dagger.Component;
import dagger.Module;
import jings.ex.android.com.dagger2demo.Main2Activity;
import jings.ex.android.com.dagger2demo.MainActivity;
import jings.ex.android.com.dagger2demo.di.module.DatabaseModule;
import jings.ex.android.com.dagger2demo.entity.DatabaseModuleObj;
import jings.ex.android.com.dagger2demo.scope.AppScope;
import jings.ex.android.com.dagger2demo.scope.UserScope;

/**
 * Created by jings on 2020/8/7.
 *
 *  * dependencies:????
 * 1.???????scope????
 * 2.??scope??????scope???
 *
 */
@UserScope
@Component(dependencies = {HttpComponent.class},modules = {DatabaseModule.class} )
public interface DataBaseComponenet {
    void injectMainActivity(MainActivity activity);

    void injectMain2Activity(Main2Activity activity);


}
