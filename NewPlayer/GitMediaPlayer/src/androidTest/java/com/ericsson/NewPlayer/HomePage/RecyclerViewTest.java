package com.ericsson.NewPlayer.HomePage;

/**
 * Created by eshesuy on 8/23/2017.
 */

import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.ericsson.IntentServiceIdlingResource;
import com.ericsson.NewPlayer.MainActivityNP;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.net.GsonRequest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by dannyroa on 5/8/15.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest{
    private IntentServiceIdlingResource idlingResource;
    private CountDownLatch lock = new CountDownLatch(1);
    private List<VideoData.VideosBean> videoList;

    @Rule
    public ActivityTestRule<MainActivityNP> mActivityTestRule = new ActivityTestRule<>(MainActivityNP.class);

    @Before
    public void registerIntentServiceIdlingResource() throws InterruptedException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(idlingResource);

        Thread.sleep(5000);
        String movieRquestUrl = "http://150.236.223.172:8008/db";
        RequestQueue requestQueue = Volley.newRequestQueue(mActivityTestRule.getActivity());
        GsonRequest<VideoData> vdieoDataRequest = new GsonRequest<VideoData>(movieRquestUrl, VideoData.class, null, new Response.Listener<VideoData>() {
            @Override
            public void onResponse(VideoData videoData) {
                videoList = videoData.getVideos();
                lock.countDown();
            }
        }, null);
        requestQueue.add(vdieoDataRequest);

        lock.await( 5 * 1000, TimeUnit.MILLISECONDS);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void testItemTitles() throws InterruptedException {
        //Click the full displayed items, both ok

//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(1, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(2, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(3, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(4, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(5, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(6, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(7, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(8, click()));
//        onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(9, click()));

        for (int index = 0; index < videoList.size(); index++){
            onView(withId(R.id.rcv_homeUI_view)).perform(actionOnItemAtPosition(index, click()));
            onView(withId(R.id.rcv_homeUI_view)).check(matches(atPosition(index,R.id.tv_homeUI_rv_title ,withText(videoList.get(index).getTitle()))));
            onView(withId(R.id.rcv_homeUI_view)).check(matches(atPosition(index,R.id.tv_homeUI_rv_subTitle ,withText(videoList.get(index).getSubtitle()))));
        }

  }

    public static Matcher<View> atPosition(final int position, final int Rid,@NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                View textView = viewHolder.itemView.findViewById(Rid);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(textView);
            }
        };
    }


}
