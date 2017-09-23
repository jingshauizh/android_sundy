package com.rengwuxian.rxjavasamples.module.test;

import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by eqruvvz on 9/12/2017.
 */

public class RxVolleyRequest {
    //该方法返回的是一个Observable，这种的是还需要进行在一步封装的，用泛型，这里我就不处理了的，也不会用的，给大家一个例子，也就是说我们完全可以把请求网络屏蔽了。
    public static Observable<JSONObject> getRequestObservable(final int method, final String url) {
        return Observable.create(new Observable.OnSubscribe<JSONObject>() {
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {
                try {
                    //stringRequest方法是用volley请示数据
                    subscriber.onNext(stringRequest(method, url));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public static JSONObject stringRequest(int method, String url) throws Exception {
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(method, url, requestFuture, requestFuture);
        VolleyManager.getRequestQueue().add(stringRequest);
        JSONObject rootJson = new JSONObject(requestFuture.get());
        return rootJson;
    }
}