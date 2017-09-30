/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.ericsson.mvp.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ericsson.NewPlayer.R;
import com.ericsson.NewPlayer.VideoData;
import com.ericsson.mvp.presenter.ActivityPresenter;
import com.ericsson.net.GsonRequest;
import com.google.gson.Gson;


public class LoginActivity extends ActivityPresenter<LoginView> implements View.OnClickListener,
        ILoginModel.OnLoginFinishedListener {

    private final String TAG = "LoginActivity";
    private LoginModel loginModel;
    private String videoDataUrl = "http://150.236.223.175:8008/db";
    private LoginView loginview;
    //"https://api.thinkpage.cn/v3/weather/now.json?key=rot2enzrehaztkdk&location=guangzhou&language=zh-Hans&unit=c"

    private static RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginModel = new LoginModel(this);
        loginview = this.view;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,videoDataUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //String s即为服务器返回的数据
                        Log.d("cylog", s);
                        Gson gson = new Gson();
                        VideoData videoData = gson.fromJson(s, VideoData.class);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("cylog", volleyError.getMessage(),volleyError);
            }
        });
        GsonRequest<VideoData> vdieoDataRequest = new GsonRequest<VideoData>(videoDataUrl, VideoData.class, null, new Response.Listener<VideoData>() {
            @Override
            public void onResponse(VideoData videoData) {
                Log.d(TAG, "getVideoData success, size = " + videoData.getVideos().size());
                Log.d(TAG,"comment: " + videoData.getVideos().get(1).getComments().get(1).getComment());
            }
        }, null);

        //3、将请求添加进请求队列
        //requestQueue.add(stringRequest);
        requestQueue.add(vdieoDataRequest);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            validateCredentials(loginview.getUsername(), loginview.getPassword());
        }
    }

    private void validateCredentials(String username, String password) {
        if (loginview != null) {
            loginview.showProgress();
        }

        loginModel.login(username, password, this);
    }

    @Override
    public void onUsernameError() {
        if (loginview != null) {
            loginview.setUsernameError();
            loginview.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginview != null) {
            loginview.setPasswordError();
            loginview.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginview != null) {
            loginview.hideProgress();
            Log.d(TAG, "login success");
            Toast toast = Toast.makeText(getApplicationContext(), R.string.login_success, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
