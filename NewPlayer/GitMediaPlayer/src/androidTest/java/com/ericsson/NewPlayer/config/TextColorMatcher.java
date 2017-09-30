package com.ericsson.NewPlayer.config;

import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;

/**
 * Created by ezhayib on 8/21/2017.
 */

public class TextColorMatcher extends BoundedMatcher<View, TextView> {

    private final int expectedId;
    private Matcher<Integer> integerMatcher;



    private TextColorMatcher(int expectedId) {
        super(TextView.class);
        this.expectedId = expectedId;
    }

    public static Matcher<View> withTextColor(@ColorRes int textColor) {
        return new TextColorMatcher(textColor);
    }

    @Override
    public boolean matchesSafely(TextView textView) {
        Resources resources = textView.getResources();
        integerMatcher = is(resources.getColor(expectedId));
        return integerMatcher.matches(textView.getCurrentTextColor());
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        mismatchDescription.appendText("TextView with text color: " +
                ((TextView) item).getCurrentTextColor() + ", expected: ");
        integerMatcher.describeMismatch(item, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with text color ");
        description.appendValue(expectedId);
    }
}
