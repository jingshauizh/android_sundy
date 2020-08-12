package jings.ex.android.com.dagger2demo.s;

import jings.ex.android.com.dagger2demo.scope.UserScope;

/**
 * Created by jings on 2020/8/10.
 */
@UserScope
@dagger.Component(modules = {CarModule.class})
public interface Component {
    void injectCar(Car car);

}
