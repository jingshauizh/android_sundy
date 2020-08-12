package jings.ex.android.com.dagger2demo.b;

/**
 * Created by jings on 2020/8/10.
 */

@dagger.Component(modules = {CarModule.class})
public interface Component {
    void injectCar(Car car);

}
