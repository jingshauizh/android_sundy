package com.ericsson.NewPlayer;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.ericsson.IntentServiceIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ericsson.NewPlayer.config.EspressoTestsMatchers.withDrawable;
import static com.ericsson.NewPlayer.config.TextColorMatcher.withTextColor;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityNPTest {

	private IntentServiceIdlingResource idlingResource;

	@Rule
	public ActivityTestRule<MainActivityNP> mActivityTestRule = new ActivityTestRule<>(MainActivityNP.class);

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

	@Test
	public void bottomMenuBarTest() {
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withText("首页")));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withText("搜索")));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withText("缓存")));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withText("推荐")));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withText("我的")));

		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_homepage_press))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_search_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_cache_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_recommend_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_userpage_normal))).check(matches(isDisplayed()));

//		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withTextColor(R.color.main_botton_text_select)));
//		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
//		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
//		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
//		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));

	}

	@Test
	public void switchToSearchPageTest() {
		//switch to search page
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).perform(click());
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_homepage_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_search_press))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_cache_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_recommend_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_userpage_normal))).check(matches(isDisplayed()));

		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withTextColor(R.color.main_botton_text_select)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));

		onView(withText("search page")).check(matches(isDisplayed()));


	}

	@Test
	public void switchToCachePageTest() {
		//switch to cache page
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).perform(click());
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_homepage_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_search_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_cache_press))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_recommend_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_userpage_normal))).check(matches(isDisplayed()));

		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withTextColor(R.color.main_botton_text_select)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));

		onView(withText("cache fragment ")).check(matches(isDisplayed()));
	}

	@Test
	public void switchToRecommendPageTest() {
		//switch to recommend page
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).perform(click());
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_homepage_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_search_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_cache_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_recommend_press))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_userpage_normal))).check(matches(isDisplayed()));

		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withTextColor(R.color.main_botton_text_select)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));

		onView(withText("recommand page")).check(matches(isDisplayed()));
	}

	@Test
	public void switchToUserPageTest() {
		//switch to user page
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).perform(click());
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_homepage_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_search_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_cache_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_recommend_normal))).check(matches(isDisplayed()));
		onView(allOf(withId(R.id.tab_iv_image), withDrawable(R.drawable.main_bottom_userpage_press))).check(matches(isDisplayed()));

		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_homepage_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_search_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_cache_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_recommend_text))).check(matches(withTextColor(R.color.main_bottom_text_normal)));
		onView(allOf(withId(R.id.tab_tv_text), withText(R.string.main_userpage_text))).check(matches(withTextColor(R.color.main_botton_text_select)));

		onView(withText("user page")).check(matches(isDisplayed()));
	}


}
