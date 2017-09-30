package com.ericsson;

import android.content.Context;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ezhayib on 8/17/2017.
 */

public class IntentServiceIdlingResource implements IdlingResource {
    private ResourceCallback resourceCallback;
    private final Context context;
    // Idleness is controlled with this boolean.
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    public IntentServiceIdlingResource(Context context) {
        this.context = context;
    }

    @Override
    public String getName() {
        return IntentServiceIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }

}
