package com.ericsson.NewPlayer.HomePage;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.ericsson.IntentServiceIdlingResource;
import com.ericsson.NewPlayer.MainActivityNP;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.net.GsonRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ericsson.NewPlayer.config.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by eshesuy on 8/18/2017.
 */
public class HomeMoviesFragmentTest {
    private IntentServiceIdlingResource idlingResource;
    private List<VideoData.VideosBean> videoList;
    private CountDownLatch lock = new CountDownLatch(1);

    @Rule
    public ActivityTestRule<MainActivityNP> mActivityTestRule = new ActivityTestRule<>(MainActivityNP.class);

    @Before
    public void registerIntentServiceIdlingResource() throws InterruptedException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(idlingResource);

        String movieRquestUrl = "http://150.236.223.172:8008/db";
        RequestQueue requestQueue = Volley.newRequestQueue(mActivityTestRule.getActivity());
        GsonRequest<VideoData> vdieoDataRequest = new GsonRequest<VideoData>(movieRquestUrl, VideoData.class, null, new Response.Listener<VideoData>() {
            @Override
            public void onResponse(VideoData videoData) {
                //Log.d(TAG, "getVideoData success, size = " + videoData.getVideos().size());
                //Log.d(TAG, "comment: " + videoData.getVideos().get(1).getComments().get(1).getComment());
                //lisenter.onSuccess(videoData.getVideos());
                videoList = videoData.getVideos();
                lock.countDown();
            }
        }, null);
        //3、将请求添加进请求队列
        requestQueue.add(vdieoDataRequest);

        lock.await( 5 * 1000, TimeUnit.MILLISECONDS);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    // This test file is for the Scrollable LoopViewPager in the Home Page
    @Test
    public void image() throws InterruptedException {
        //Test the image URL in the LoopViewPager, have not finished as don't know how to verify remote Picture
        //for sentences is disturbed by the auto scroller
        //for (int index = 0; index < 5; index++) {
            onView(allOf(withId(R.id.frag_pagerHeader), isDisplayed())).perform(swipeLeft());

            onView(withId(R.id.frag_pagerHeader)).perform(click());
            Thread.sleep(2000);

            ViewInteraction appCompatImageButton1 = onView(
                    allOf(withId(R.id.play_info_icon), isDisplayed()));
            appCompatImageButton1.check(matches(withDrawable(R.drawable.info)));
            //Such below check should be finished later on
            //onView(withId(R.id.frag_pagerHeader)).check(matches(withImage(videoList.get(index).getVideoImageUrl())));
        //}

        // back to the homepage from the playback page
        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.play_back), isDisplayed()));
        appCompatImageButton4.perform(click());


        //Test the TV Series Name
        onView(withId(R.id.homepage_recommendview_title)).check(matches(withText("足球")));

        //Test the CircleIndicator
        onView(withId(R.id.frag_ci)).check(matches(isDisplayed()));

   }




}