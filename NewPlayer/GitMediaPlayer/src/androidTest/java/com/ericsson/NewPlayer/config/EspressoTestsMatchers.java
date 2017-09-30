package com.ericsson.NewPlayer.config;

import android.view.View;

import org.hamcrest.Matcher;

public class EspressoTestsMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    //‘noDrawable()’ method just in case you want to test that the ImageView doesn’t have any image associated.
    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(DrawableMatcher.EMPTY);
    }

    public static Matcher<View> hasDrawable() {
        return new DrawableMatcher(DrawableMatcher.ANY);
    }
}
