package com.ericsson.mvp.demo;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.ericsson.IntentServiceIdlingResource;
import com.ericsson.NewPlayer.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;


//import static org.hamcrest.Matchers.endsWith;

/**
 * Created by eshesuy on 8/11/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginViewTest {

    private static final String USER_NAME = "Sarah";
    private static final String PASSWD = "1234";

    private IntentServiceIdlingResource idlingResource;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Before
    public void registerIntentServiceIdlingResource() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    //    @Test
    public void getUsername() throws Exception {

    }

    //    @Test
    public void getPassword() throws Exception {

    }

    //    @Test
    public void showProgress() throws Exception {

    }

    @Test
    public void loginSuccessfully() throws Exception {
        onView(withId(R.id.username)).perform(typeText(USER_NAME),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(PASSWD),closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.login_success))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void getUsernameNullError() throws Exception {
        // When the use name is null
        String USER_NAME_NULL = "Username cannot be empty";

        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.username)).check(matches(hasErrorText(USER_NAME_NULL)));
    }

    @Test
    public void getPasswordNullError() throws Exception {
        // When the password is null
        String PASSWORD_NAME_NULL = "Password cannot be empty";

        ViewInteraction editText = onView(
                allOf(withId(R.id.username), isDisplayed()));
        editText.perform(click());

        // Input one user:sarah
        ViewInteraction editText2 = onView(
                allOf(withId(R.id.username), isDisplayed()));
        editText2.perform(replaceText("sarah"), closeSoftKeyboard());

        // Press Log in button
        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("Log in"), isDisplayed()));
        button.perform(click());

        // Added a sleep statement to match the app's execution delay.

        // Click the ! icon
        onView(withId(R.id.password)).perform(click());

        // Added a sleep statement to match the app's execution delay.

        // Check the password null error
        onView(withId(R.id.password)).check(matches(hasErrorText(PASSWORD_NAME_NULL)));
    }

}


