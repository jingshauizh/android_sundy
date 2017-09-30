package com.ericsson.homefragment;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ericsson.NewPlayer.MainActivityNP;
import com.ericsson.NewPlayer.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ericsson.NewPlayer.config.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTestUiActivityTest {

    @Rule
    public ActivityTestRule<MainActivityNP> mActivityTestRule = new ActivityTestRule<>(MainActivityNP.class);

    @Test
    public void search() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check the Search picture is correct
        onView(
                allOf(withId(R.id.tab_iv_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.tabs),
                                        1),
                                0))).check(matches(withDrawable(R.drawable.main_bottom_search_normal)));

        // Perform click the Search button
        onView(
                allOf(withId(R.id.tab_iv_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.tabs),
                                        1),
                                0))).perform(click());

        //Check the Search Text is correct
        ViewInteraction textView = onView(
                allOf(withId(R.id.tab_tv_text), withText("搜索"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.tabs),
                                        1),
                                1))).
                        check(matches(withText("搜索")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
