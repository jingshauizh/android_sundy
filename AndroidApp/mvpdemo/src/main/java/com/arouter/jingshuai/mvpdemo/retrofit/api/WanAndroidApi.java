package com.arouter.jingshuai.mvpdemo.retrofit.api;

import com.arouter.jingshuai.mvpdemo.retrofit.model.ProjectBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

/**
 * Created by jings on 2020/6/5.
 */

public interface WanAndroidApi {

    @GET("project/tree/json")
    Call<ProjectBean> getProject();

    @HTTP(method = "get", path = "project/tree/json")
    Call<ResponseBody> example();
}
