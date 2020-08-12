package jings.ex.android.com.dagger2demo.c;

import javax.inject.Inject;
import javax.inject.Named;
import jings.ex.android.com.dagger2demo.c.DaggerComponent;

/**
 * Created by jings on 2020/8/10.
 */

public class Car {
    @Named("engineA")
    @Inject
    Engine engineA;

    @Named("engineB")
    @Inject
    Engine engineB;


    public Car() {
        DaggerComponent.builder().build().injectCar(this);
    }

    public static void main(String [] arg){
        Car car = new Car();
        Engine engine = car.engineA;
        System.out.println(engine);
        System.out.println(engine.getName());

        Engine engineB = car.engineB;
        System.out.println(engineB);
        System.out.println(engineB.getName());

    }

  




}
