package jings.ex.android.com.dagger2demo.b;

import javax.inject.Inject;

/**
 * Created by jings on 2020/8/10.
 */


public class Engine {

    private String name ;


    public Engine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
