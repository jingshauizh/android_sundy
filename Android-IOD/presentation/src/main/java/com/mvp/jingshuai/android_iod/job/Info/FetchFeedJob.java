/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mvp.jingshuai.android_iod.job.Info;

import android.content.Context;
import android.support.annotation.Nullable;


import com.mvp.jingshuai.android_iod.api.ApiModule;
import com.mvp.jingshuai.android_iod.api.ApiService;
import com.mvp.jingshuai.android_iod.api.FeedResponse;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.android_iod.di.component.AppComponent;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.android_iod.job.NetworkException;
import com.mvp.jingshuai.android_iod.vo.Post;
import com.path.android.jobqueue.Params;
import com.path.android.jobqueue.RetryConstraint;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

public class FetchFeedJob extends BaseJob {

    private static final String GROUP = "FetchFeedJob";
    private final Long mUserId;

    @Inject
    transient EventBus mEventBus;

    @Inject
    transient ApiService mApiService;

    public FetchFeedJob(Context context, @Priority int priority, @Nullable Long userId) {
       // super(new Params(priority).addTags(GROUP).requireNetwork());
        super(new Params(priority).requireNetwork().groupBy("fetch-tweets"));
        mUserId = userId;
    }

    @Override
    public void inject(AppComponent appComponent) {
        super.inject(appComponent);
        appComponent.inject(this);
    }



    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        long timestamp = 0;
        final Call<FeedResponse> feed;

        feed = mApiService.feed(timestamp);

        Response<FeedResponse> response = feed.execute();
        if (response.isSuccess()) {
            Post oldest = handleResponse(response.body());
            //mEventBus.post(new FetchedFeedEvent(true, mUserId, oldest));
        } else {
            throw new NetworkException(response.code());
        }
    }

    @Nullable
    private Post handleResponse(FeedResponse body) {
        // We could put these two into a transaction but it is OK to save a user even if we could
        // not save their post so we don't care.

        Post oldest = null;
        if (body.getPosts() != null) {
           // mPostModel.saveAll(body.getPosts());
            long since = 0;
            for (Post post : body.getPosts()) {
                if (post.getCreated() > since) {
                    since = post.getCreated();
                }
                if (oldest == null || oldest.getCreated() > post.getCreated()) {
                    oldest = post;
                }
            }
            if (since > 0) {
               // mFeedModel.saveFeedTimestamp(since, mUserId);
            }
        }
        return oldest;
    }

    @Override
    public RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount,
                                                  int maxRunCount) {
        if (shouldRetry(throwable)) {
            return RetryConstraint.createExponentialBackoff(runCount, 1000);
        }
        return RetryConstraint.CANCEL;
    }

    @Override
    protected int getRetryLimit() {
        return 2;
    }
    @Override
    protected void onCancel() {

    }

}
