package com.ericsson.NewPlayer.Play;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ericsson.NewPlayer.HomePage.RecyclerViewTest.atPosition;
import static com.ericsson.NewPlayer.config.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.allOf;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Created by eshiyda on 2017/9/5.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)

public class PlaybackPage {
    private IntentServiceIdlingResource idlingResource;
    private CountDownLatch lock = new CountDownLatch(1);
    private List<VideoData.VideosBean> videoList;
    int PositionNumber = 1;//PositionNumber is the target tested video position

    @Rule
    public ActivityTestRule<MainActivityNP> mActivityTestRule = new ActivityTestRule<>(MainActivityNP.class);


    @Before
    public void registerIntentServiceIdlingResource() throws InterruptedException{
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

    @Before
    public void setUp() throws Exception{
            ViewInteraction recyclerView = onView(
                    allOf(withId(R.id.rcv_homeUI_view), isDisplayed()));
            recyclerView.perform(actionOnItemAtPosition(PositionNumber, click()));
            recyclerView.perform(actionOnItemAtPosition(PositionNumber, click()));
    }

    @Test
    public void aPlayerActivity() throws InterruptedException {
        //check the video info
        ViewInteraction appCompatImageButton1 = onView(
                allOf(withId(R.id.play_info_icon), isDisplayed()));
        appCompatImageButton1.check(matches(withDrawable(R.drawable.info)));
        appCompatImageButton1.perform(click());
        onView(withId(R.id.play_title)).check(matches(withText(videoList.get(PositionNumber).getTitle())));
        onView(withId(R.id.play_subtitle)).check(matches(withText(videoList.get(PositionNumber).getSubtitle())));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.play_info_icon), isDisplayed()));
        appCompatImageButton2.check(matches(withDrawable(R.drawable.close)));
        appCompatImageButton2.perform(click());

        //Check the favorite status
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.play_like),
                        withParent(withId(R.id.frameLayout)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        appCompatImageButton3.check(matches(withDrawable(R.drawable.heart_fav)));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.play_like),
                        withParent(withId(R.id.frameLayout)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        appCompatImageButton4.check(matches(withDrawable(R.drawable.heart_unfav)));

    }

    @Test
    public void bRelatedVideo() throws InterruptedException {
        for (int index = 0; index < videoList.size(); index++) {
            onView(withId(R.id.play_related_view)).perform(actionOnItemAtPosition(index, click()));
            onView(withId(R.id.play_related_view)).check(matches(atPosition(index,R.id.tv_homeUI_rv_title ,withText(videoList.get(index).getTitle()))));
            onView(withId(R.id.play_related_view)).check(matches(atPosition(index,R.id.tv_homeUI_rv_subTitle ,withText(videoList.get(index).getSubtitle()))));
        }
    }

    @Ignore
    public void cVideoComments() throws InterruptedException {
        //To be implemented
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.play_comment_view), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @After
    public void exitPlayer(){
        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.play_back), isDisplayed()));
        appCompatImageButton4.perform(click());

    }
}
