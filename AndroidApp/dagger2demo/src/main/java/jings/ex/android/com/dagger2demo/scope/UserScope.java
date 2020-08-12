package jings.ex.android.com.dagger2demo.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by jings on 2020/8/7.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface UserScope {
}
