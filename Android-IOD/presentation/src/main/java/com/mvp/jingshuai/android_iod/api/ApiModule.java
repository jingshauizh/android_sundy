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

package com.mvp.jingshuai.android_iod.api;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvp.jingshuai.android_iod.config.DemoConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {


    @Provides
    @Singleton
    public ApiService apiService(DemoConfig demoConfig) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl(demoConfig.getApiUrl())
                .addConverterFactory(JacksonConverterFactory.create(mapper)).build()
                .create(ApiService.class);
    }
}
