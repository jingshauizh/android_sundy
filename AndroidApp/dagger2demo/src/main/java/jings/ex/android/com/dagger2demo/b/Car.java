package jings.ex.android.com.dagger2demo.b;

import javax.inject.Inject;
import jings.ex.android.com.dagger2demo.b.DaggerComponent;

/**
 * Created by jings on 2020/8/10.
 */

public class Car {
    @Inject
    Engine engine;


    public Car() {
        DaggerComponent.builder().build().injectCar(this);
    }

    public static void main(String [] arg){
        Car car = new Car();
        Engine engine = car.engine;
        System.out.println(engine);
        System.out.println(engine.getName());

    }

  




}
