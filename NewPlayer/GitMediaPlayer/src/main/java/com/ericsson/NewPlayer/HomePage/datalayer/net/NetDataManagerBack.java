package com.ericsson.NewPlayer.HomePage.datalayer.net;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.ericsson.NewPlayer.HomePage.datalayer.localdb.MovieIdal;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieList;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieModel;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.moviefrag.IMovieFragModel;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by eqruvvz on 9/11/2017.
 */

public class NetDataManagerBack {
    private final String TAG = "NetDataManager";
    private MovieIdal _MovieIdal;
    private CompositeDisposable _disposables ;
    private String movieRquestUrl = "http://150.236.223.172:8008/db";
    private IMovieFragModel.OnPageRefreshListener mlisenter ;

    public NetDataManagerBack(){
        _MovieIdal = new MovieIdal();
        _disposables = new CompositeDisposable();
    }

    public void retuestMoviesList(MovieType pMovieType,final IMovieFragModel.OnPageRefreshListener lisenter) {
        mlisenter = lisenter;
        DisposableSubscriber<JSONObject> d =
                new DisposableSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        Log.e(TAG, "onNext " + jsonObject.toString());
                        Gson gson = new Gson();

                        MovieList videoDataList =
                                gson.fromJson(jsonObject.toString(), MovieList.class);

                        //save data todo
                        List<MovieModel> ovieModellist = videoDataList.castResultToMovieModellist();
                        _MovieIdal.saveAll(ovieModellist);
                        if(mlisenter !=null){
                            mlisenter.onSuccess();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        VolleyError cause = (VolleyError) e.getCause();
                        if(cause.networkResponse != null){
                            String s = new String(cause.networkResponse.data, Charset.forName("UTF-8"));
                            Log.e(TAG, s);
                            Log.e(TAG, cause.toString());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onCompleted");
                        if(mlisenter !=null){
                            mlisenter.onSuccess();
                        }
                    }
                };



//        Subscriber<JSONObject> mySubscriber = new Subscriber<JSONObject>() {
//            @Override
//            public void onSubscribe(Subscription subscription) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onCompleted.................");
//            }
//
//            @Override
//            public void onNext(JSONObject jsonObject) {
//                Log.e(TAG, "onNext " + jsonObject.toString());
//                Gson gson = new Gson();
//
//                MovieList videoDataList =
//                        gson.fromJson(jsonObject.toString(), MovieList.class);
//
//                //save data todo
//                List<MovieModel> ovieModellist = videoDataList.castResultToMovieModellist();
//                _MovieIdal.saveAll(ovieModellist);
//                lisenter.onSuccess();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError....................");
//                VolleyError cause = (VolleyError) e.getCause();
//                        if(cause.networkResponse != null){
//                            String s = new String(cause.networkResponse.data, Charset.forName("UTF-8"));
//                            Log.e(TAG, s);
//                            Log.e(TAG, cause.toString());
//                        }
//            }
//        };

        //createObserable()
        newGetRouteData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d);

        //_disposables.add(mySubscriber);
    }

    private JSONObject getRouteData() throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, movieRquestUrl, future, future);
        MyVolley.getRequestQueue().add(req);
        return future.get();
    }


    public Observable<JSONObject> createObserable() {

        try {
            Observable<JSONObject> ob = Observable.just(getRouteData());
            return ob;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Flowable<JSONObject> newGetRouteData() {
        return Flowable.defer(new Callable<Flowable<? extends JSONObject>>() {
            @Override
            public Flowable<? extends JSONObject> call() throws Exception {
                try {
                    return Flowable.just(getRouteData());
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("routes", e.getMessage());
                    return Flowable.error(e);
                }
            }
        });

    }

    public void onDestroy(){
        _disposables.clear();
    }


}
